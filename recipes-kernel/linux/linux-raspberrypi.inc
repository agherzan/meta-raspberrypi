DESCRIPTION = "Linux Kernel for Raspberry Pi"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

COMPATIBLE_MACHINE = "^rpi$"

PE = "1"
PV = "${LINUX_VERSION}+git${SRCPV}"

inherit kernel siteinfo
require recipes-kernel/linux/linux-yocto.inc

KCONFIG_MODE = "--alldefconfig"
KBUILD_DEFCONFIG_raspberrypi0-wifi ?= "bcmrpi_defconfig"
KBUILD_DEFCONFIG_raspberrypi ?= "bcmrpi_defconfig"
KBUILD_DEFCONFIG_raspberrypi2 ?= "bcm2709_defconfig"
KBUILD_DEFCONFIG_raspberrypi3 ?= "bcm2709_defconfig"
KBUILD_DEFCONFIG_raspberrypi3-64 ?= "bcmrpi3_defconfig"

# CMDLINE for raspberrypi
CMDLINE ?= "dwc_otg.lpm_enable=0 console=serial0,115200 root=/dev/mmcblk0p2 rootfstype=ext4 rootwait"

# Add the kernel debugger over console kernel command line option if enabled
CMDLINE_append = ' ${@oe.utils.conditional("ENABLE_KGDB", "1", "kgdboc=serial0,115200", "", d)}'

# Disable rpi logo on boot
CMDLINE_append += ' ${@oe.utils.conditional("DISABLE_RPI_BOOT_LOGO", "1", "logo.nologo", "", d)}'

# You can define CMDLINE_DEBUG as "debug" in your local.conf or distro.conf
# to enable kernel debugging.
CMDLINE_DEBUG ?= ""
CMDLINE_append = " ${CMDLINE_DEBUG}"

# Quirk for udev greater or equal 141
UDEV_GE_141 ?= "1"

# Enable OABI compat for people stuck with obsolete userspace
ARM_KEEP_OABI ?= "1"

KERNEL_INITRAMFS ?= '${@oe.utils.conditional("INITRAMFS_IMAGE_BUNDLE", "1", "1", "", d)}'

KERNEL_MODULE_AUTOLOAD += "${@bb.utils.contains("MACHINE_FEATURES", "pitft28r", "stmpe-ts", "", d)}"

# A LOADADDR is needed when building a uImage format kernel. This value is not
# set by default in rpi-4.8.y and later branches so we need to provide it
# manually. This value unused if KERNEL_IMAGETYPE is not uImage.
KERNEL_EXTRA_ARGS += "LOADADDR=0x00008000"

# Set a variable in .configure
# $1 - Configure variable to be set
# $2 - value [n/y/value]
kernel_configure_variable() {
    # Remove the config
    CONF_SED_SCRIPT="$CONF_SED_SCRIPT /CONFIG_$1[ =]/d;"
    if test "$2" = "n"
    then
        echo "# CONFIG_$1 is not set" >> ${B}/.config
    else
        echo "CONFIG_$1=$2" >> ${B}/.config
    fi
}

config_setup() {
    # From kernel.bbclass. Unfortunately, this is needed to support builds that
    # use devtool. The reason is as follows:
    #
    # - In devtool builds, externalsrc.bbclass gets inherited and sets a list of
    # SRCTREECOVEREDTASKS, which don't get run because they affect the source
    # tree and, when using devtool, we want the developer's changes to be the
    # single source of truth. kernel-yocto.bbclass adds do_kernel_configme to
    # SRCTREECOVEREDTASKS, so it doesn't run in a devtool build., In a normal
    # non-devtool build, do_kernel_configme creates ${B}.config.
    #
    # - Normally (e.g. in linux-yocto), it would be OK that do_kernel_configme
    # doesn't run, because the first few lines of do_configure in kernel.bbclass
    # populate ${B}.config from either ${S}.config (if it exists) for custom
    # developer changes, or otherwise from ${WORDIR}/defconfig.
    #
    # - In linux-raspberrypi, we add do_configure_prepend, which tweaks
    # ${B}.config. Since this runs *before* the kernel.bbclass do_configure,
    # ${B}.config doesn't yet exist and we hit an error. Thus we need to move
    # the logic from do_configure up to before our do_configure_prepend. Because
    # we are copying only a portion of do_configure and not the whole thing,
    # there is no clean way to do it using OE functionality, so we just
    # copy-and-paste.
    if [ "${S}" != "${B}" ] && [ -f "${S}/.config" ] && [ ! -f "${B}/.config" ]; then
        mv "${S}/.config" "${B}/.config"
    fi

    # Copy defconfig to .config if .config does not exist. This allows
    # recipes to manage the .config themselves in do_configure_prepend().
    if [ -f "${WORKDIR}/defconfig" ] && [ ! -f "${B}/.config" ]; then
        cp "${WORKDIR}/defconfig" "${B}/.config"
    fi
}

do_configure_prepend() {
    config_setup

    mv -f ${B}/.config ${B}/.config.patched
    CONF_SED_SCRIPT=""

    # oabi / eabi support
    kernel_configure_variable AEABI y
    if [ "${ARM_KEEP_OABI}" = "1" ] ; then
        kernel_configure_variable OABI_COMPAT y
    else
        kernel_configure_variable OABI_COMPAT n
    fi

    # Set cmdline
    kernel_configure_variable CMDLINE "\"${CMDLINE}\""

    # Localversion
    kernel_configure_variable LOCALVERSION "\"\""
    kernel_configure_variable LOCALVERSION_AUTO n

    # Udev quirks
    # Newer versions of udev mandate that sysfs doesn't have deprecated entries
    if [ "${UDEV_GE_141}" = "1" ] ; then
        kernel_configure_variable SYSFS_DEPRECATED n
        kernel_configure_variable SYSFS_DEPRECATED_V2 n
        kernel_configure_variable HOTPLUG y
        kernel_configure_variable UEVENT_HELPER_PATH "\"\""
        kernel_configure_variable UNIX y
        kernel_configure_variable SYSFS y
        kernel_configure_variable PROC_FS y
        kernel_configure_variable TMPFS y
        kernel_configure_variable INOTIFY_USER y
        kernel_configure_variable SIGNALFD y
        kernel_configure_variable TMPFS_POSIX_ACL y
        kernel_configure_variable BLK_DEV_BSG y
        kernel_configure_variable DEVTMPFS y
        kernel_configure_variable DEVTMPFS_MOUNT y
    fi

    # Newer inits like systemd need cgroup support
    if [ "${KERNEL_ENABLE_CGROUPS}" = "1" ] ; then
        kernel_configure_variable CGROUP_SCHED y
        kernel_configure_variable CGROUPS y
        kernel_configure_variable CGROUP_NS y
        kernel_configure_variable CGROUP_FREEZER y
        kernel_configure_variable CGROUP_DEVICE y
        kernel_configure_variable CPUSETS y
        kernel_configure_variable PROC_PID_CPUSET y
        kernel_configure_variable CGROUP_CPUACCT y
        kernel_configure_variable RESOURCE_COUNTERS y
    fi

    # root-over-nfs-over-usb-eth support. Limited, but should cover some cases
    # Enable this by setting a proper CMDLINE_NFSROOT_USB.
    if [ ! -z "${CMDLINE_NFSROOT_USB}" ]; then
        oenote "Configuring the kernel for root-over-nfs-over-usb-eth with CMDLINE ${CMDLINE_NFSROOT_USB}"
        kernel_configure_variable INET y
        kernel_configure_variable IP_PNP y
        kernel_configure_variable USB_GADGET y
        kernel_configure_variable USB_GADGET_SELECTED y
        kernel_configure_variable USB_ETH y
        kernel_configure_variable NFS_FS y
        kernel_configure_variable ROOT_NFS y
        kernel_configure_variable ROOT_NFS y
        kernel_configure_variable CMDLINE "\"${CMDLINE_NFSROOT_USB}\""
    fi
    if [ ! -z "${KERNEL_INITRAMFS}" ]; then
        kernel_configure_variable BLK_DEV_INITRD y
        kernel_configure_variable INITRAMFS_SOURCE ""
        kernel_configure_variable RD_GZIP y
        kernel_configure_variable OVERLAY_FS y
        kernel_configure_variable SQUASHFS y
        kernel_configure_variable UBIFS_FS y
    fi
    # Activate CONFIG_LEGACY_PTYS
    kernel_configure_variable LEGACY_PTYS y
    # this module is built externally via drbd-utils
    kernel_configure_variable BLK_DEV_DRBD n

    # Activate the configuration options for VC4
    VC4GRAPHICS="${@bb.utils.contains("MACHINE_FEATURES", "vc4graphics", "1", "0", d)}"
    if [ ${VC4GRAPHICS} = "1" ]; then
        kernel_configure_variable I2C_BCM2835 y
        kernel_configure_variable DRM y
        kernel_configure_variable DRM_FBDEV_EMULATION y
        kernel_configure_variable DRM_VC4 y
    fi

    # Keep this the last line
    # Remove all modified configs and add the rest to .config
    sed -e "${CONF_SED_SCRIPT}" < '${B}/.config.patched' >> '${B}/.config'
    rm -f ${B}/.config.patched
}

do_compile_append_raspberrypi3-64() {
    cc_extra=$(get_cc_option)
    oe_runmake dtbs CC="${KERNEL_CC} $cc_extra " LD="${KERNEL_LD}" ${KERNEL_EXTRA_ARGS}
}

do_deploy_append() {
    # Deploy cmdline.txt
    install -d ${DEPLOYDIR}/bcm2835-bootfiles
    PITFT="${@bb.utils.contains("MACHINE_FEATURES", "pitft", "1", "0", d)}"
    if [ ${PITFT} = "1" ]; then
        PITFT_PARAMS="fbcon=map:10 fbcon=font:VGA8x8"
    fi
    echo "${CMDLINE}${PITFT_PARAMS}" > ${DEPLOYDIR}/bcm2835-bootfiles/cmdline.txt
}
