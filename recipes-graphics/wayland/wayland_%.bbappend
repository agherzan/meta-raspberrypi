# remove provided `libwayland-egl` library in favour of the version in `userland` until fully tested

do_install_append_rpi () {
    rm -f ${D}${libdir}/libwayland-egl*
    rm -f ${D}${libdir}/pkgconfig/wayland-egl.pc
}
