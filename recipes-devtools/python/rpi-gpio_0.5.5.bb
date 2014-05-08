DESCRIPTION = "A module to control Raspberry Pi GPIO channels"
HOMEPAGE = "http://code.google.com/p/raspberry-gpio-python/"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENCE.txt;md5=35af90ff2a10e8bdc967653b9dfcb22a"

SRCNAME = "RPi.GPIO"

SRC_URI = "\
          http://pypi.python.org/packages/source/R/RPi.GPIO/${SRCNAME}-${PV}.tar.gz \
          "
S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit distutils

COMPATIBLE_MACHINE = "raspberrypi"

SRC_URI[md5sum] = "8cbc1cb0c0f1a4d93bf1efe1a745f1f0"
SRC_URI[sha256sum] = "c8e1f0da6327103a3551a6e86055ef001113b94af683fcb08070db5f5ecea51f"
