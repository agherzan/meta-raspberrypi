FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.4.8"

SRCREV = "fe7ad6aa5c6940817fe971d80a7b2a1d6052190a"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.4.y \
"
require linux-raspberrypi.inc
