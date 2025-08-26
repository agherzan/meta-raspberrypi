SUMMARY = "libpisp - PISP library"
DESCRIPTION = "Library for PISP pipeline handler"

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3417a46e992fdf62e5759fba9baef7a7"

SRC_URI = "git://github.com/raspberrypi/libpisp.git;protocol=https;branch=main"
PV = "1.2.1+git${SRCPV}"
SRCREV = "981977ff21f32c8a97d2a0ecbdff3e39d42ccce3"

inherit meson pkgconfig

DEPENDS = "\
  boost \
  nlohmann-json \
"

CXXFLAGS:append = " -Wno-unused-result "

INSANE_SKIP:${PN} = "buildpaths"
INSANE_SKIP:${PN}-dev = "buildpaths"

