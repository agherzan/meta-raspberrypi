require userland.inc

PROVIDES += "${@bb.utils.contains("MACHINE_FEATURES", "vc4graphics", "", "virtual/libgles2 virtual/egl", d)}"
PROVIDES += "virtual/libomxil"

RPROVIDES_${PN} += "${@bb.utils.contains("MACHINE_FEATURES", "vc4graphics", "", "libgles2 egl libegl libegl1 libglesv2-2", d)}"
COMPATIBLE_MACHINE = "^rpi$"

RCONFLICTS_${PN} = "userlandtools"

SRC_URI += "\
    file://0001-Allow-applications-to-set-next-resource-handle.patch \
    file://0002-wayland-Add-support-for-the-Wayland-winsys.patch \
    file://0003-wayland-Add-Wayland-example.patch \
    file://0004-wayland-egl-Add-bcm_host-to-dependencies.patch \
    file://0005-interface-remove-faulty-assert-to-make-weston-happy-.patch \
    file://0006-zero-out-wl-buffers-in-egl_surface_free.patch \
    file://0007-initialize-front-back-wayland-buffers.patch \
    file://0008-Remove-RPC_FLUSH.patch \
    file://0009-fix-cmake-dependency-race.patch \
    file://0010-Fix-for-framerate-with-nested-composition.patch \
    file://0011-build-shared-library-for-vchostif.patch \
    file://0012-implement-buffer-wrapping-interface-for-dispmanx.patch \
    file://0013-Implement-triple-buffering-for-wayland.patch \
    file://0014-GLES2-gl2ext.h-Define-GL_R8_EXT-and-GL_RG8_EXT.patch \
    file://0015-EGL-glplatform.h-define-EGL_CAST.patch \
    file://0016-Allow-multiple-wayland-compositor-state-data-per-pro.patch \
    file://0017-khronos-backport-typedef-for-EGL_EXT_image_dma_buf_i.patch \
    file://0018-Add-EGL_IMG_context_priority-related-defines.patch \
    file://0019-libfdt-Undefine-__wordsize-if-already-defined.patch \
    file://0020-openmaxil-add-pkg-config-file.patch \
    file://0021-cmake-Disable-format-overflow-warning-as-error.patch \
"

PACKAGECONFIG ?= "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'wayland', '', d)}"

PACKAGECONFIG[wayland] = "-DBUILD_WAYLAND=TRUE -DWAYLAND_SCANNER_EXECUTABLE:FILEPATH=${STAGING_BINDIR_NATIVE}/wayland-scanner,,wayland-native wayland"

CFLAGS_append = " -fPIC"

do_install_append () {
	for f in `find ${D}${includedir}/interface/vcos/ -name "*.h"`; do
		sed -i 's/include "vcos_platform.h"/include "pthreads\/vcos_platform.h"/g' ${f}
		sed -i 's/include "vcos_futex_mutex.h"/include "pthreads\/vcos_futex_mutex.h"/g' ${f}
		sed -i 's/include "vcos_platform_types.h"/include "pthreads\/vcos_platform_types.h"/g' ${f}
	done
        rm -rf ${D}${prefix}${sysconfdir}
	if [ "${@bb.utils.contains("MACHINE_FEATURES", "vc4graphics", "1", "0", d)}" = "1" ]; then
		rm -rf ${D}${libdir}/libEGL*
		rm -rf ${D}${libdir}/libGLES*
		rm -rf ${D}${libdir}/libwayland-*
		rm -rf ${D}${libdir}/pkgconfig/egl.pc ${D}${libdir}/pkgconfig/glesv2.pc \
			${D}${libdir}/pkgconfig/wayland-egl.pc
		rm -rf ${D}${includedir}/EGL ${D}${includedir}/GLES* ${D}${includedir}/KHR
        else
                ln -sf brcmglesv2.pc ${D}${libdir}/pkgconfig/glesv2.pc
                ln -sf brcmegl.pc ${D}${libdir}/pkgconfig/egl.pc
                ln -sf brcmvg.pc ${D}${libdir}/pkgconfig/vg.pc
	fi
}

FILES_${PN} += " \
    ${libdir}/*.so \
    ${libdir}/plugins"
FILES_${PN}-dev += "${includedir} \
                   ${prefix}/src"
FILES_${PN}-doc += "${datadir}/install"
FILES_${PN}-dbg += "${libdir}/plugins/.debug"

RDEPENDS_${PN} += "${@bb.utils.contains("MACHINE_FEATURES", "vc4graphics", "libegl-mesa", "", d)}"
