DESCRIPTION = "Linux Kernel for Raspberry Pi"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

COMPATIBLE_MACHINE = "^rpi$"

PE = "1"
PV = "${LINUX_VERSION}+git${SRCPV}"

inherit kernel siteinfo
require recipes-kernel/linux/linux-yocto.inc

SRC_URI += " \
    file://rpi-kernel-misc.cfg \
    ${@bb.utils.contains("INITRAMFS_IMAGE_BUNDLE", "1", "file://initramfs-image-bundle.cfg", "", d)} \
    ${@bb.utils.contains("MACHINE_FEATURES", "vc4graphics", "file://vc4graphics.cfg", "", d)} \
    "

KCONFIG_MODE = "--alldefconfig"
KBUILD_DEFCONFIG_raspberrypi0-wifi ?= "bcmrpi_defconfig"
KBUILD_DEFCONFIG_raspberrypi ?= "bcmrpi_defconfig"
KBUILD_DEFCONFIG_raspberrypi-cm3 ?= "bcm2709_defconfig"
KBUILD_DEFCONFIG_raspberrypi2 ?= "bcm2709_defconfig"
KBUILD_DEFCONFIG_raspberrypi3 ?= "bcm2709_defconfig"
KBUILD_DEFCONFIG_raspberrypi3-64 ?= "bcmrpi3_defconfig"
KBUILD_DEFCONFIG_raspberrypi4 ?= "bcm2711_defconfig"
KBUILD_DEFCONFIG_raspberrypi4-64 ?= "bcm2711_defconfig"

LINUX_VERSION_EXTENSION ?= ""

# CMDLINE for raspberrypi
SERIAL = "${@oe.utils.conditional("ENABLE_UART", "1", "console=serial0,115200", "", d)}"
CMA ?= "${@oe.utils.conditional("RASPBERRYPI_CAMERA_V2", "1", "cma=64M", "", d)}"
CMDLINE ?= "dwc_otg.lpm_enable=0 ${SERIAL} root=/dev/mmcblk0p2 rootfstype=ext4 rootwait ${CMA}"

# Add the kernel debugger over console kernel command line option if enabled
CMDLINE_append = ' ${@oe.utils.conditional("ENABLE_KGDB", "1", "kgdboc=serial0,115200", "", d)}'

# Disable rpi logo on boot
CMDLINE_append = ' ${@oe.utils.conditional("DISABLE_RPI_BOOT_LOGO", "1", "logo.nologo", "", d)}'

# You can define CMDLINE_DEBUG as "debug" in your local.conf or distro.conf
# to enable kernel debugging.
CMDLINE_DEBUG ?= ""
CMDLINE_append = " ${CMDLINE_DEBUG}"

KERNEL_MODULE_AUTOLOAD += "${@bb.utils.contains("MACHINE_FEATURES", "pitft28r", "stmpe-ts", "", d)}"

# A LOADADDR is needed when building a uImage format kernel. This value is not
# set by default in rpi-4.8.y and later branches so we need to provide it
# manually. This value unused if KERNEL_IMAGETYPE is not uImage.
KERNEL_EXTRA_ARGS += "LOADADDR=0x00008000"

do_compile_append() {
    if [ "${SITEINFO_BITS}" = "64" ]; then
        cc_extra=$(get_cc_option)
        oe_runmake dtbs CC="${KERNEL_CC} $cc_extra " LD="${KERNEL_LD}" ${KERNEL_EXTRA_ARGS}
    fi
}

do_deploy_append() {
    # Deploy cmdline.txt only for the main kernel package
    if [ ${KERNEL_PACKAGE_NAME} = "kernel" ]; then
        install -d ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}
        PITFT="${@bb.utils.contains("MACHINE_FEATURES", "pitft", "1", "0", d)}"
        if [ ${PITFT} = "1" ]; then
            PITFT_PARAMS="fbcon=map:10 fbcon=font:VGA8x8"
        fi
        echo "${CMDLINE}${PITFT_PARAMS}" > ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/cmdline.txt
    fi
}
