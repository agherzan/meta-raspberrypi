SUMMARY = "suite of libcamera-based applications"
DESCRIPTION = "This is a small suite of libcamera-based applications to \
drive the cameras on a Raspberry Pi platform."
HOMEPAGE = "https://github.com/raspberrypi/rpicam-apps"
SECTION = "console/utils"

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://license.txt;md5=a0013d1b383d72ba4bdc5b750e7d1d77"

SRC_URI = "\
    git://github.com/raspberrypi/rpicam-apps.git;protocol=https;branch=main \
"
PV = "1.9.1+git${SRCPV}"
SRCREV = "6782818adcb8b5559c2657bb144e78d69607681f"

DEPENDS = "rpi-libcamera libexif jpeg tiff libpng boost"

PACKAGECONFIG ??= "drm"
PACKAGECONFIG[libav] = "-Denable_libav=enabled, -Denable_libav=disabled, libav"
PACKAGECONFIG[drm] = "-Denable_drm=enabled, -Denable_drm=disabled, libdrm"
PACKAGECONFIG[egl] = "-Denable_egl=enabled, -Denable_egl=disabled, virtual/egl"
PACKAGECONFIG[qt] = "-Denable_qt=enabled, -Denable_qt=disabled, qtbase"
PACKAGECONFIG[opencv] = "-Denable_opencv=enabled, -Denable_opencv=disabled, opencv"
PACKAGECONFIG[tflite] = "-Denable_tflite=enabled, -Denable_tflite=disabled, tensorflow-lite"

inherit meson pkgconfig

NEON_FLAGS = ""
NEON_FLAGS:aarch64 = "-Dneon_flags=arm64"
NEON_FLAGS:arm:raspberrypi3 = "-Dneon_flags=armv8-neon"
NEON_FLAGS:arm:raspberrypi4 = "-Dneon_flags=armv8-neon"
EXTRA_OEMESON += "${NEON_FLAGS}"

# QA Issue: /usr/bin/camera-bug-report contained in package libcamera-apps requires /usr/bin/python3
do_install:append() {
    rm -v ${D}/${bindir}/camera-bug-report
}

# not picked automatically, because it's missing common 'lib' prefix
FILES:${PN}-dev += "${libdir}/rpicam_app.so"

FILES:${PN} += "\
    ${libdir}/rpicam_app.so.${@d.getVar("PV", False).__str__().split('+')[0]} \
    ${libdir}/rpicam-apps* \
    ${datadir}/rpi-camera-assets/* \
"

INSANE_SKIP:${PN}-dev = "buildpaths"
