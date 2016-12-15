FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.8.13"

SRCREV = "430587d6949e71d5eec9cf6ebd90106c11d2e08c"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.8.y \
"
require linux-raspberrypi.inc
