FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.8.12"

SRCREV = "ca6ab40b8e878f96f44ab48bfddef299a82aa683"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.8.y \
"
require linux-raspberrypi.inc
