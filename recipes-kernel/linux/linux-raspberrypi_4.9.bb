FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.9.13"

SRCREV = "28ea32b9afb5d813986c4ab940c26fe298d80ed2"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.9.y \
"
require linux-raspberrypi.inc
