SUMMARY = "A suite of libcamera-based apps"
DESCRIPTION = "This is a small suite of libcamera-based apps that aim to \
copy the functionality of the existing \"raspicam\" apps."
HOMEPAGE = "https://github.com/raspberrypi/libcamera-apps"
SECTION = "console/utils"

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://license.txt;md5=a0013d1b383d72ba4bdc5b750e7d1d77"

SRC_URI = "\
    git://github.com/raspberrypi/libcamera-apps.git;protocol=https;branch=main \
    file://0001-utils-version.py-use-usr-bin-env-in-shebang.patch \
"
PV = "1.5.2+git${SRCPV}"
SRCREV = "50958df98d3cf77b54706a794226d556d649981c"

DEPENDS = "libcamera libexif jpeg tiff libpng boost"

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

FILES:${PN}:append = " \
  /usr/share/rpi-camera-assets/negate.json \
  /usr/share/rpi-camera-assets/hdr.json \
  /usr/share/rpi-camera-assets/motion_detect.json \
  /usr/lib/rpicam-apps-postproc/core-postproc.so \
"

# not picked automatically, because it's missing common 'lib' prefix
FILES:${PN}-dev += "${libdir}/rpicam_app.so"

FILES:${PN} += "${libdir}/rpicam_app.so.${@d.getVar("PV", False).__str__().split('+')[0]}"
