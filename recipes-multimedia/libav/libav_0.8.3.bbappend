FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
PRINC = "1"

SRC_URI_append = " file://libavcodec-ac3dsp_armv6-patch-out-armv7.patch"

EXTRA_OECONF_append_raspberrypi = " \
        --cpu=arm1176jzf-s --disable-armvfp --disable-neon \
"
