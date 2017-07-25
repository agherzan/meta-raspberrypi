FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.9:"

LINUX_VERSION ?= "4.10.0-rc8"

SRCREV = "9aa91f43583fdb07306f962e6ee6797fba1cc62d"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.10.y \
           file://0001-build-arm64-Add-rules-for-.dtbo-files-for-dts-overla.patch \
"
require linux-raspberrypi.inc
