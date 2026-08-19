SUMMARY = "Python interface to the Raspberry Pi camera module via libcamera"
DESCRIPTION = "This package provides a pure Python interface to the Raspberry Pi camera module via libcamera for Python 3"
HOMEPAGE = "https://github.com/raspberrypi/picamera2" 

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6541a38108b5accb25bd55a14e76086d"

RDEPENDS:${PN} = "python3-numbers   \
                  python3-ctypes    \
                  python3-colorzero \
                  picamera-libs     \
"

SRC_URI = "git://git@github.com/raspberrypi/picamera2.git;protocol=ssh;branch=main"
SRCREV = "fb031cb49cb2af4dab7439be7a90784c9d3db1ab"

S = "${WORKDIR}/git"

inherit setuptools3

COMPATIBLE_HOST = "null"
COMPATIBLE_HOST:rpi:libc-glibc = "(arm.*)-linux"
