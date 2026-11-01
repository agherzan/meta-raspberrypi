SUMMARY = "Python library for the Raspberry Pi Build HAT"
DESCRIPTION = "Python library for the Raspberry Pi Build HAT, an add-on board \
that allows control of LEGO Technic motors and SPIKE Portfolio sensors. \
Talks to the HAT over /dev/serial0 and uses GPIO through gpiozero."
HOMEPAGE = "https://github.com/RaspberryPiFoundation/python-build-hat"
SECTION = "devel/python"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f575f0cdf46f5275f0e9864074d9ac7e"

SRC_URI[sha256sum] = "87b197b22a10664038cfe1f6f9d46cdb4ce52185936ba0276d028de15157dff7"

# The PyPI project is called "buildhat"
PYPI_PACKAGE = "buildhat"

inherit pypi setuptools3

RDEPENDS:${PN} += " \
    python3-asyncio \
    python3-gpiozero \
    python3-io \
    python3-logging \
    python3-pyserial \
    python3-threading \
"
