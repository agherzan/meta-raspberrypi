SUMMARY = "Raspberry libcamera-apps"

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://license.txt;md5=a0013d1b383d72ba4bdc5b750e7d1d77"

SRC_URI = "git://github.com/raspberrypi/libcamera-apps.git;branch=main;protocol=https"

PV = "1.0+git${SRCPV}"
SRCREV = "6145daf735fcb0a6e26699a407fe80307c24f6db"

S = "${WORKDIR}/git"

DEPENDS = "libcamera opencv boost libjpeg-turbo libexif tiff"

inherit cmake pkgconfig

# Specify extra options:
# EXTRA_OECMAKE = "-DENABLE_DRM=0 -DENABLE_X11=0 -DENABLE_QT=0 -DENABLE_OPENCV=1 -DENABLE_TFLITE=0"

FILES:${PN}-dev = "${includedir} ${libdir}/pkgconfig"
FILES:${PN} += "${libdir}/libpreview.so"
FILES:${PN} += "${libdir}/libencoders.so"
FILES:${PN} += "${libdir}/liboutputs.so"
FILES:${PN} += "${libdir}/libimages.so"
FILES:${PN} += "${libdir}/libpost_processing_stages.so"
FILES:${PN} += "${libdir}/libcamera_app.so"
