FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.4.39"

SRCREV = "5e46914b3417fe9ff42546dcacd0f41f9a0fb172"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.4.y \
           file://0001-fix-dtbo-rules.patch \
"
require linux-raspberrypi.inc
