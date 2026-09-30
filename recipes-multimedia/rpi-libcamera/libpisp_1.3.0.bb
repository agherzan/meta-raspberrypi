SUMMARY = "libpisp - PISP library"
DESCRIPTION = "Library for PISP pipeline handler"

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3417a46e992fdf62e5759fba9baef7a7"

SRC_URI = "git://github.com/raspberrypi/libpisp.git;protocol=https;branch=main"
PV = "1.3.0+git${SRCPV}"
SRCREV = "9ba67e6680f03f31f2b1741a53e8fd549be82cbe"

inherit meson pkgconfig

DEPENDS = "\
  boost \
  nlohmann-json \
"

CXXFLAGS:append = " -Wno-unused-result "

INSANE_SKIP:${PN} = "buildpaths"
INSANE_SKIP:${PN}-dev = "buildpaths"

