COMPATIBLE_MACHINE = "raspberrypi"

require linux.inc

DESCRIPTION = "Linux kernel for the RaspberryPi board"

# Bump MACHINE_KERNEL_PR in the machine config if you update the kernel.
# This is on the rpi-patches branch
SRCREV = "0ec4154d64ebba48ca2446cde60a90546311defc"

SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-patches \
"          

LINUX_VERSION ?= "3.1.9-rpi"
PV = "${LINUX_VERSION}+${PR}+git${SRCREV}"

S = "${WORKDIR}/git"

# NOTE: For now we pull in the default config from the RPi kernel GIT tree.

KERNEL_DEFCONFIG = "bcmrpi_defconfig"

do_configure_prepend() {
	install -m 0644 ${S}/arch/${ARCH}/configs/${KERNEL_DEFCONFIG} ${WORKDIR}/defconfig || die "No default configuration for ${MACHINE} / ${KERNEL_DEFCONFIG} available."
}
