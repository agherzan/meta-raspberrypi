PACKAGECONFIG:append:rpi = " gbm ${@bb.utils.contains('MACHINE_FEATURES', 'vc4graphics', '', 'gallium', d)}"
PROVIDES:append:rpi = " virtual/libgbm"

GALLIUMDRIVERS:append:rpi = ",swrast"

do_install:append:rpi() {
    rm -rf ${D}${includedir}/KHR/khrplatform.h
}
