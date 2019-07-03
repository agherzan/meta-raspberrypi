FILESEXTRAPATHS_prepend := "${THISDIR}/linux-raspberrypi:"

LINUX_VERSION ?= "4.19.56"
LINUX_RPI_BRANCH ?= "rpi-4.19.y"

SRCREV = "fba6ea6a8f6e2eaf814475812aec64de3f4912c9"
SRC_URI = " \
    git://github.com/raspberrypi/linux.git;protocol=git;branch=${LINUX_RPI_BRANCH} \
    "
SRC_URI_append_raspberrypi4-64 = " file://rpi4-64-kernel-misc.cfg"
require linux-raspberrypi.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
