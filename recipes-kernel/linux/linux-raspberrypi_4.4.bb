FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.4.7"

SRCREV = "3de232825c9ba5989522b8691eb6ac5df6619458"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.4.y \
           file://0001-dts-add-overlay-for-pitft22.patch \
"

require linux-raspberrypi.inc
