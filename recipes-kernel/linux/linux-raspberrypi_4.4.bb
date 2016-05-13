FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.4.9"

SRCREV = "3b440738b5c1adc3ec3ee72ceca799d1b8d264df"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.4.y \
"
require linux-raspberrypi.inc
