FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.7.0"

SRCREV = "d38b45a21294eaf01aa7568aaeb161d7553477e9"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.7.y \
           file://0001-fix-dtbo-rules.patch \
"
require linux-raspberrypi.inc
