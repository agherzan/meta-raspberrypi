SUMMARY = "Raspberry Pi ISP (PiSP)"
SECTION = "libs"

LICENSE = "BSD-2-Clause"

LIC_FILES_CHKSUM = " file://LICENSES/BSD-2-Clause.txt;md5=3417a46e992fdf62e5759fba9baef7a7"

SRC_URI = " \
        git://github.com/raspberrypi/libpisp.git;protocol=https;branch=main \
"

SRCREV = "50426319aa1a9ba4672f91977429365ad4e335a2"

PV = "1.2.0"

S = "${WORKDIR}/git"

DEPENDS = "nlohmann-json"

INSANE_SKIP:${PN} = "buildpaths"

inherit meson pkgconfig