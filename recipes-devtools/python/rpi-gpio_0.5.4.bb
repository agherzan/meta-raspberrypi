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

SRC_URI[md5sum] = "add6554ed8331d515fccb5e052c7d1ff"
SRC_URI[sha256sum] = "5fae8e59a9a1cdb68208d40239d7ad788d22aa9d58c54c482fa5d89ccc4c0f37"
