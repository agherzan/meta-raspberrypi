FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.9.80"

SRCREV = "027885087faa515133534cf767a1cd66cbd6cd1e"
SRC_URI = "git://github.com/raspberrypi/linux.git;branch=rpi-4.9.y"

require linux-raspberrypi.inc
