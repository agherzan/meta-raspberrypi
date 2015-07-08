DESCRIPTION = "A module to control Raspberry Pi GPIO channels"
HOMEPAGE = "http://code.google.com/p/raspberry-gpio-python/"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENCE.txt;md5=9b95630a648966b142f1a0dcea001cb7"

SRCNAME = "RPi.GPIO"

SRC_URI = "\
          http://pypi.python.org/packages/source/R/RPi.GPIO/${SRCNAME}-${PV}.tar.gz \
          "
S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit distutils

COMPATIBLE_MACHINE = "raspberrypi"

SRC_URI[md5sum] = "9dc3dab6ce2b7ccb833a866efb392821"
SRC_URI[sha256sum] = "8d6f02da7f90e24512ad80ee4ccf34ef33687c88c47326f100cf7ac4d7ae4bf3"
