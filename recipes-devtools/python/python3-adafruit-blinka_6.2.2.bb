SUMMARY = "CircuitPython APIs for non-CircuitPython versions of Python such as CPython on Linux and MicroPython."
HOMEPAGE = "https://github.com/adafruit/Adafruit_Blinka"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=660e614bc7efb0697cc793d8a22a55c2"

SRC_URI = "git://github.com/adafruit/Adafruit_Blinka.git"
SRCREV = "dc688f354fe779c9267c208b99f310af87e79272"

S = "${WORKDIR}/git"

inherit setuptools3

DEPENDS += "python3-setuptools-scm-native"

RDEPENDS_${PN} += " \
    libgpiod \
    python3-adafruit-platformdetect \
    python3-adafruit-pureio \
    python3-core \
"

RDEPENDS_${PN}_append_rpi = " rpi-gpio"
