SUMMARY = "Raspberry libcamera-apps"

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://license.txt;md5=a0013d1b383d72ba4bdc5b750e7d1d77"

SRC_URI = "git://github.com/raspberrypi/libcamera-apps.git;branch=main;protocol=https"

PV = "1.0+git${SRCPV}"
SRCREV = "11c5203a202777ddc339bba1dd8f85b53b60c279"

S = "${WORKDIR}/git"

DEPENDS = "libcamera opencv boost libjpeg-turbo libexif tiff"
RDEPENDS:${PN} = "libcamera opencv boost libjpeg-turbo libexif tiff python3-core"

inherit cmake pkgconfig

EXTRA_OECMAKE = "${@bb.utils.contains("DISTRO_FEATURES", "x11", "-DENABLE_X11=1", "-DENABLE_X11=0", d)} \
    ${@bb.utils.contains("MACHINE", "raspberrypi3", "-DENABLE_COMPILE_FLAGS_FOR_TARGET=armv8-neon", "", d)} \
    ${@bb.utils.contains("MACHINE", "raspberrypi4", "-DENABLE_COMPILE_FLAGS_FOR_TARGET=armv8-neon", "", d)} \
"

PACKAGECONFIG[qt] = "-DENABLE_QT=1"
PACKAGECONFIG[opencv] = "-DENABLE_OPENCV=1"
PACKAGECONFIG[tensorflow-lite] = "-DENABLE_TFLITE=1"

COMPATIBLE_MACHINE = "^rpi$"

FILES:${PN}-dev = "${includedir} ${libdir}/pkgconfig"
FILES:${PN} += "${libdir}/libpreview.so"
FILES:${PN} += "${libdir}/libencoders.so"
FILES:${PN} += "${libdir}/liboutputs.so"
FILES:${PN} += "${libdir}/libimages.so"
FILES:${PN} += "${libdir}/libpost_processing_stages.so"
FILES:${PN} += "${libdir}/libcamera_app.so"
