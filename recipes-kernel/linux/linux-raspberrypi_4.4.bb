FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.4.6"

SRCREV = "2faaa2ccef9e4c595bd26f14285c225ceea6097e"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.4.y \
           file://0001-dts-add-overlay-for-pitft22.patch \
"

require linux-raspberrypi.inc
