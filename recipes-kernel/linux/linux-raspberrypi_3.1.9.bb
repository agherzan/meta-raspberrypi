require linux.inc

DESCRIPTION = "Linux kernel for the RaspberryPi board"
KERNEL_IMAGETYPE = "zImage"

COMPATIBLE_MACHINE = "raspberrypi"

# This is on the rpi-patches branch
SRCREV = "2fb814968753d0d4a5fdf056a3a8c9eee3c41aa6"

SRC_URI = " \
           git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-patches \
"          

PV = "3.1.9-rpi+${PR}+git${SRCREV}"

S = "${WORKDIR}/git"

# NOTE: For now we pull in the default config from the RPi kernel GIT tree.

KERNEL_DEFCONFIG = "bcmrpi_defconfig"

do_configure_prepend() {
	install -m 0644 ${S}/arch/${ARCH}/configs/${KERNEL_DEFCONFIG} ${WORKDIR}/defconfig || die "No default configuration for ${MACHINE} / ${KERNEL_DEFCONFIG} available."
}
