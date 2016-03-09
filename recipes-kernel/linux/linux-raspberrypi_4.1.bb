FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.1.18"

SRCREV = "fcc9ad149fbe78e9931b8e769ac54d5430587465"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.1.y \
           file://0001-dts-add-overlay-for-pitft22.patch \
          "

require linux-raspberrypi.inc
