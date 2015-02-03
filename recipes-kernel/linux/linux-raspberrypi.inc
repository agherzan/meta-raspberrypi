FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

inherit kernel
require recipes-kernel/linux/linux-yocto.inc

DESCRIPTION = "Linux Kernel for Raspberry Pi"

PV_append = "+git${SRCREV}"

SRC_URI = " \
    git://github.com/raspberrypi/linux.git;protocol=git;branch=${KBRANCH} \
    file://sl030raspberrypii2ckernel.patch \
    file://defconfig \
    "
S = "${WORKDIR}/git"

CMDLINE ?= " \
    dwc_otg.lpm_enable=0 console=ttyAMA0,115200 kgdboc=ttyAMA0,115200 \
    root=/dev/mmcblk0p2 rootfstype=ext4 rootwait"

do_deploy_append() {
    # Deploy cmdline.txt
    install -d ${DEPLOYDIR}/bcm2835-bootfiles
    echo "${CMDLINE}" > ${DEPLOYDIR}/bcm2835-bootfiles/cmdline.txt
}

COMPATIBLE_MACHINE = "raspberrypi"
