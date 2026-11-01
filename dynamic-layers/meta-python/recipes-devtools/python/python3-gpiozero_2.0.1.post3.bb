SUMMARY = "A simple interface to GPIO devices with Raspberry Pi"
DESCRIPTION = "gpiozero provides a simple, pythonic API for GPIO devices and \
is the library used by the Build HAT Python library to drive the HAT reset \
and boot pins."
HOMEPAGE = "https://gpiozero.readthedocs.io/"
SECTION = "devel/python"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.rst;md5=f7edfe7aeac02cb6c394726db07eb41c"

SRC_URI[sha256sum] = "745feab6df463ac2e9de10c67e2dd9f396e668ba4e281e92381d6c460100a8f7"

PYPI_PACKAGE = "gpiozero"

inherit pypi setuptools3

# python3-misc provides webbrowser (used by the pinout command)
RDEPENDS:${PN} += " \
    python3-colorzero \
    python3-datetime \
    python3-io \
    python3-logging \
    python3-math \
    python3-misc \
    python3-mmap \
    python3-numbers \
    python3-pprint \
    python3-statistics \
    python3-threading \
"
