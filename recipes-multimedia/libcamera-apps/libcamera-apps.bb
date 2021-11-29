# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   license.txt
LICENSE = "Unknown"
LIC_FILES_CHKSUM = "file://license.txt;md5=a0013d1b383d72ba4bdc5b750e7d1d77"

SRC_URI = "git://github.com/raspberrypi/libcamera-apps.git;branch=main;protocol=https"

# Modify these as desired
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
