FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.8.6"

SRCREV = "6abac13566786086cd912d87e4f1a922e2a391b2"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.8.y \
"
require linux-raspberrypi.inc
