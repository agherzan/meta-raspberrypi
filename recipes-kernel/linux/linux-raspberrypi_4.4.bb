FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.4.16"

SRCREV = "cff67c7e03f4333149f2a8f6eafd3bc44475114a"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.4.y \
           file://0001-fix-dtbo-rules.patch \
           file://0002-vc4-ioctl-rendering-allow.patch \
"
require linux-raspberrypi.inc
