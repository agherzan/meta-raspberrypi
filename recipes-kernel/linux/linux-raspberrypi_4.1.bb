FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.1.17"

SRCREV = "cb2f10196a9b718a2d94bb4ac0887c2ea14988ae"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.1.y \
           file://0001-dts-add-overlay-for-pitft22.patch \
          "

require linux-raspberrypi.inc
