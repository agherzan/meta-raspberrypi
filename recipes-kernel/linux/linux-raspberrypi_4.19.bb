FILESEXTRAPATHS_prepend := "${THISDIR}/linux-raspberrypi:"

LINUX_VERSION ?= "4.19.57"
LINUX_RPI_BRANCH ?= "rpi-4.19.y"

SRCREV = "55b89745a4782db50e86291bd1adee8fb44b84ad"
SRC_URI = " \
    git://github.com/raspberrypi/linux.git;protocol=git;branch=${LINUX_RPI_BRANCH} \
    "
SRC_URI_append_raspberrypi4-64 = " file://rpi4-64-kernel-misc.cfg"

require linux-raspberrypi.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

KERNEL_EXTRA_ARGS_append_rpi = " DTC_FLAGS='-@ -H epapr'"
