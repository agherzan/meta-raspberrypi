require linux.inc

DESCRIPTION = "Linux kernel for the OpenPandora handheld"
KERNEL_IMAGETYPE = "uImage"

COMPATIBLE_MACHINE = "raspberrypi"

# This is on the pandora-3.2 branch
SRCREV = "d348f1694041b0310e42c6a471e97214eb301e91"

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
