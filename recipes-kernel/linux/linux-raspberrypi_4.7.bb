FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.7.7"

SRCREV = "a45a35c08598b23f9fd82e49c83b46c7d536244f"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.7.y \
           file://0001-fix-dtbo-rules.patch \
"
require linux-raspberrypi.inc
