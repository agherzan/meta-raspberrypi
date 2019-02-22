PACKAGECONFIG_append_rpi = " gbm"

do_install_append_rpi() {
    if [ "${@bb.utils.contains("MACHINE_FEATURES", "vc4graphics", "1", "0", d)}" = "0" ]; then
        rm -rf ${D}${libdir}/libEGL*
        rm -rf ${D}${libdir}/libGLES*
        rm -rf ${D}${libdir}/libwayland-*
        rm -rf ${D}${libdir}/pkgconfig/egl.pc ${D}${libdir}/pkgconfig/glesv2.pc \
            ${D}${libdir}/pkgconfig/wayland-egl.pc
        rm -rf ${D}${includedir}/EGL ${D}${includedir}/GLES* ${D}${includedir}/KHR
    fi
}
