FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "3.14.28"

SRCREV = "e294028d7733a30f3befacc41d473c251096a515"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-3.14.y \
           file://0001-ASoC-Add-BCM2708-fixes.patch \
           file://0002-Fix-grabbing-lock-from-atomic-context-in-i2c-driver.patch \
          "

require linux-raspberrypi.inc
