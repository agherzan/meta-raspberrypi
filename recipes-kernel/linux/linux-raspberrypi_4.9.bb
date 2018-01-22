FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.9.77"

SRCREV = "c7b6645458053c75009559e66bd5e3034e82969f"
SRC_URI = "git://github.com/raspberrypi/linux.git;branch=rpi-4.9.y"

require linux-raspberrypi.inc
