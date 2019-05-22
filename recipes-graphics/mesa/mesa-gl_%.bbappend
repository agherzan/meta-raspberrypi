FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"
SRC_URI += "file://0001-Allow-enable-DRI-without-DRI-drivers.patch"

PACKAGECONFIG_append_rpi = " gbm"
PROVIDES_append_rpi = " virtual/libgbm"

do_install_append_rpi() {
    rm -rf ${D}${includedir}/KHR/khrplatform.h
}

EXTRA_OEMESON += "-Ddri=true"
