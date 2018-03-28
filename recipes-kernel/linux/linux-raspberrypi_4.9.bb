FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.9.80"

SRCREV = "a7b4dd27c1c0d6510b8066b91ef01be0928d8529"
SRC_URI = "git://github.com/raspberrypi/linux.git;branch=rpi-4.9.y"

require linux-raspberrypi.inc
