COMPATIBLE_MACHINE = "raspberrypi"

require linux.inc

DESCRIPTION = "Linux kernel for the RaspberryPi board"

PR = "r3"

# Bump MACHINE_KERNEL_PR in the machine config if you update the kernel.
# This is on the rpi-patches branch
SRCREV = "14ad68cf4afc7acf14076f895b539d81cd9f32ab"

SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-patches \
"          

LINUX_VERSION ?= "3.1.9-rpi"
PV = "${LINUX_VERSION}+${PR}+git${SRCREV}"

S = "${WORKDIR}/git"

# NOTE: For now we pull in the default config from the RPi kernel GIT tree.

KERNEL_DEFCONFIG = "bcmrpi_defconfig"

# CMDLINE for raspberrypi
CMDLINE_raspberrypi = "dwc_otg.lpm_enable=0 console=ttyAMA0,115200 kgdboc=ttyAMA0,115200 root=/dev/mmcblk0p2 rootfstype=ext4 rootwait"

PARALLEL_MAKEINST = ""

UDEV_GE_141 ?= "1"

do_configure_prepend() {
	install -m 0644 ${S}/arch/${ARCH}/configs/${KERNEL_DEFCONFIG} ${WORKDIR}/defconfig || die "No default configuration for ${MACHINE} / ${KERNEL_DEFCONFIG} available."
}

do_install_prepend() {
	install -d ${D}/lib/firmware
}
