SUMMARY = "CircuitPython APIs for non-CircuitPython versions of Python such as CPython on Linux and MicroPython."
HOMEPAGE = "https://github.com/adafruit/Adafruit_Blinka"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=660e614bc7efb0697cc793d8a22a55c2"

SRC_URI = "git://github.com/adafruit/Adafruit_Blinka.git;branch=main;protocol=https"
SRCREV = "dc688f354fe779c9267c208b99f310af87e79272"

S = "${WORKDIR}/git"

inherit setuptools3

DEPENDS += "python3-setuptools-scm-native"
# to disable qa errors when shipping 32bit bynaries in 64bit rpi machines
do_package_qa[noexec] = "1"

RDEPENDS:${PN} += " \
    libgpiod \
    python3-adafruit-platformdetect \
    python3-adafruit-pureio \
    python3-core \
"

RDEPENDS:${PN}:append:rpi = " rpi-gpio"

COMPATIBLE_HOST:libc-musl:class-target = "null"

