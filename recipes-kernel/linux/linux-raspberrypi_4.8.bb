FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.8.4"

SRCREV = "d8f7c2da3d33ea4fb4f67411badad374b1160b44"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.8.y \
"
require linux-raspberrypi.inc
