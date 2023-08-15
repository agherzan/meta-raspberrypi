SUMMARY = "A suite of libcamera-based apps"
DESCRIPTION = "This is a small suite of libcamera-based apps that aim to \
copy the functionality of the existing \"raspicam\" apps."
HOMEPAGE = "https://github.com/raspberrypi/libcamera-apps"
SECTION = "console/utils"

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://license.txt;md5=a0013d1b383d72ba4bdc5b750e7d1d77"

OBJECT_DETECT_TF ?= "coco_ssd_mobilenet_v1_1.0_quant_2018_06_29"

SRC_URI = "\
    git://github.com/raspberrypi/libcamera-apps.git;protocol=https;branch=main \
    file://0001-utils-version.py-use-usr-bin-env-in-shebang.patch \
    https://storage.googleapis.com/download.tensorflow.org/models/tflite/${OBJECT_DETECT_TF}.zip;name=object_detect_tf \
"

SRC_URI[object_detect_tf.sha256sum] = "a809cd290b4d6a2e8a9d5dad076e0bd695b8091974e0eed1052b480b2f21b6dc"

PV = "1.2.1+git${SRCPV}"
SRCREV = "1c1d1c1a2a86d70cf873edc8bb72d174f037973a"

S = "${WORKDIR}/git"

DEPENDS = "libcamera libexif jpeg tiff libpng boost"

PACKAGECONFIG ??= "drm"
PACKAGECONFIG[libav] = "-Denable_libav=true, -Denable_libav=false, libav"
PACKAGECONFIG[drm] = "-Denable_drm=true, -Denable_drm=false, libdrm"
PACKAGECONFIG[egl] = "-Denable_egl=true, -Denable_egl=false, virtual/egl"
PACKAGECONFIG[qt] = "-Denable_qt=true, -Denable_qt=false, qtbase"
PACKAGECONFIG[opencv] = "-Denable_opencv=true, -Denable_opencv=false, opencv"
PACKAGECONFIG[tflite] = "-Denable_tflite=true, -Denable_tflite=false, tensorflow-lite"

inherit meson pkgconfig

NEON_FLAGS = ""
NEON_FLAGS:aarch64 = "-Dneon_flags=arm64"
NEON_FLAGS:arm:raspberrypi3 = "-Dneon_flags=armv8-neon"
NEON_FLAGS:arm:raspberrypi4 = "-Dneon_flags=armv8-neon"
EXTRA_OEMESON += "${NEON_FLAGS}"

# QA Issue: /usr/bin/camera-bug-report contained in package libcamera-apps requires /usr/bin/python3
do_install:append() {
    rm -v ${D}/${bindir}/camera-bug-report

    install -d ${D}/usr/share/${BPN}/assets
    install -m 644 ${S}/assets/* ${D}/usr/share/${BPN}/assets

    install -d ${D}/usr/share/${BPN}/assets/${OBJECT_DETECT_TF}
    install -m 644 ${WORKDIR}/detect.tflite ${D}/usr/share/${BPN}/assets/${OBJECT_DETECT_TF}
    install -m 644 ${WORKDIR}/labelmap.txt ${D}/usr/share/${BPN}/assets/${OBJECT_DETECT_TF}
    sed -i -e "s,/home/pi/models,/usr/share/${BPN}/assets,g" ${D}/usr/share/${BPN}/assets/object_detect_tf.json
}
