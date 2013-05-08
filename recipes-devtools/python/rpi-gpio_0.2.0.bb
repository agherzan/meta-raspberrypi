DESCRIPTION = "A module to control Raspberry Pi GPIO channels"
HOMEPAGE = "http://code.google.com/p/raspberry-gpio-python/"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENCE.txt;md5=ee5754ae9d5f8061d6d4ccd9c9fe0061"

SRCNAME = "RPi.GPIO"
PR = "r0"

SRC_URI = "\
          http://pypi.python.org/packages/source/R/RPi.GPIO/${SRCNAME}-${PV}.tar.gz \
          file://don-t-install-setuptools.patch \
          "
S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit setuptools

COMPATIBLE_MACHINE = "raspberrypi"

SRC_URI[md5sum] = "0fc4bfa6aabc856b0b75252a40ac75cc"
SRC_URI[sha256sum] = "fcfd97dc9687dde76b13b9d12c122e71b13e2ba09a62913d7b8d9ddbb3e8cabf"
