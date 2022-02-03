SUMMARY = "cmdline.txt file used to boot the kernel on a Raspberry Pi device"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

COMPATIBLE_MACHINE = "^rpi$"
INHIBIT_DEFAULT_DEPS = "1"
inherit deploy nopackages

CMDLINE_DWC_OTG ?= "dwc_otg.lpm_enable=0"

CMDLINE_ROOT_FSTYPE ?= "rootfstype=ext4"
CMDLINE_ROOTFS ?= "root=/dev/mmcblk0p2 ${CMDLINE_ROOT_FSTYPE} rootwait"

CMDLINE_SERIAL ?= "${@oe.utils.conditional("ENABLE_UART", "1", "console=serial0,115200", "", d)}"

CMDLINE_CMA ?= "${@oe.utils.conditional("RASPBERRYPI_CAMERA_V2", "1", "cma=64M", "", d)}"

CMDLINE_CMA ?= "${@oe.utils.conditional("RASPBERRYPI_HD_CAMERA", "1", "cma=64M", "", d)}"

CMDLINE_PITFT ?= "${@bb.utils.contains("MACHINE_FEATURES", "pitft", "fbcon=map:10 fbcon=font:VGA8x8", "", d)}"

# Add the kernel debugger over console kernel command line option if enabled
CMDLINE_KGDB ?= '${@oe.utils.conditional("ENABLE_KGDB", "1", "kgdboc=serial0,115200", "", d)}'

# Disable rpi logo on boot
CMDLINE_LOGO ?= '${@oe.utils.conditional("DISABLE_RPI_BOOT_LOGO", "1", "logo.nologo", "", d)}'

# You can define CMDLINE_DEBUG as "debug" in your local.conf or distro.conf
# to enable kernel debugging.
CMDLINE_DEBUG ?= ""

# Add gadget capabilities (must be after rootwait)
# if the MAC addresses are omitted, random values will be used
def setup_gadget_mac(d, gadget_type):
    string = ""
    if d.getVar('GADGET_HOST_MAC_ADDR'):
        string += " " + gadget_type + ".host_addr="\
          + d.getVar('GADGET_HOST_MAC_ADDR')
    if d.getVar('GADGET_DEV_MAC_ADDR'):
        string += " " + gadget_type + ".dev_addr="\
          + d.getVar('GADGET_DEV_MAC_ADDR')
    return string

def setup_mass_storage(d, gadget_type):
    string = ""
    if d.getVar('GADGET_MASS_STORAGE_NAME'):
        string += " " + gadget_type + ".file="\
          + d.getVar('GADGET_MASS_STORAGE_NAME')
    return string

# Setup the gadget configuration
def setup_gadget_mode(d):
    string = ""
    if not bb.utils.contains('DISTRO_FEATURES', 'systemd', True, False, d)\
        or d.getVar('ENABLE_DWC2_PERIPHERAL') != "1":
            return string

    if d.getVar('ENABLE_ETHER_GADGET') == "1":
        string += "modules-load=dwc2,g_ether"

        # If the user supplies a host or a dev mac address, use it
        string += setup_gadget_mac(d, "g_ether")

    elif d.getVar('ENABLE_CDC_GADGET') == "1":
        string += "modules-load=dwc2,g_cdc"

        # If the user supplies a host or a dev mac address, use it
        string += setup_gadget_mac(d, "g_cdc")

    elif d.getVar('ENABLE_SERIAL_GADGET') == "1":
        string += "modules-load=dwc2,g_serial"

    elif d.getVar('ENABLE_MULTI_GADGET') == "1":
        string += "modules-load=dwc2,g_multi"

        # If the user supplies a mass storage name use it
        string += setup_mass_storage(d, 'g_multi')

        # If the user supplies a host or a dev mac address, use it
        string += setup_gadget_mac(d, "g_multi")

    elif d.getVar('ENABLE_MASS_STORAGE_GADGET') == "1":
        string += "modules-load=dwc2,g_mass_storage"

        # If the user supplies a mass storage name use it
        string += setup_mass_storage(d, 'g_mass_storage')

    return string

CMDLINE_GADGET_MODE ?= "${@setup_gadget_mode(d)}"

CMDLINE = " \
    ${CMDLINE_DWC_OTG} \
    ${CMDLINE_SERIAL} \
    ${CMDLINE_ROOTFS} \
    ${CMDLINE_CMA} \
    ${CMDLINE_KGDB} \
    ${CMDLINE_LOGO} \
    ${CMDLINE_PITFT} \
    ${CMDLINE_DEBUG} \
    ${CMDLINE_RNDIS} \
    "

do_compile() {
    echo "${@' '.join('${CMDLINE}'.split())}" > "${WORKDIR}/cmdline.txt"
}

do_deploy() {
    install -d "${DEPLOYDIR}/${BOOTFILES_DIR_NAME}"
    install -m 0644 "${WORKDIR}/cmdline.txt" "${DEPLOYDIR}/${BOOTFILES_DIR_NAME}"
}

addtask deploy before do_build after do_install
do_deploy[dirs] += "${DEPLOYDIR}/${BOOTFILES_DIR_NAME}"

PACKAGE_ARCH = "${MACHINE_ARCH}"
