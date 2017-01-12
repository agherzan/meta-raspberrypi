FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.8.16"

SRCREV = "061dccce6cf6705bbb5da29a643f4b0ad1d11630"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.8.y \
"
require linux-raspberrypi.inc
