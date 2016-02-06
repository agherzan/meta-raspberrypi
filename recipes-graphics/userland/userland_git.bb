DESCRIPTION = "This repository contains the source code for the ARM side \
libraries used on Raspberry Pi. These typically are installed in /opt/vc/lib \
and includes source for the ARM side code to interface to: EGL, mmal, GLESv2,\
vcos, openmaxil, vchiq_arm, bcm_host, WFC, OpenVG."
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENCE;md5=0448d6488ef8cc380632b1569ee6d196"

PR = "r5"

PROVIDES = "virtual/libgles2 \
            virtual/egl"

COMPATIBLE_MACHINE = "raspberrypi"

SRCBRANCH = "master"
SRCFORK = "raspberrypi"
SRCREV = "2a4af2192c0e161555fdb2a12e902b587166c4a6"

SRC_URI = "\
    git://github.com/${SRCFORK}/userland.git;protocol=git;branch=${SRCBRANCH} \
    file://0001-Fix-VCOS_INLINE_DECL-to-not-use-extern-inlines-in-de.patch \
    file://0002-musl-inspired-fixed.patch \
    file://0003-set-VMCS_INSTALL_PREFIX-to-usr.patch \
    file://0004-cmake-generate-and-install-pkgconfig-files.patch \
    file://0005-user-vcsm-Fix-build-with-clang.patch \
    file://0006-Fix-enum-type-conversion-warnings.patch \
    file://0007-vcos_platform_types-Dont-use-extern-inline-with-clan.patch \
    file://0008-Allow-applications-to-set-next-resource-handle.patch \
    file://0009-wayland-Add-support-for-the-Wayland-winsys.patch \
    file://0010-wayland-Add-Wayland-example.patch \
    file://0011-wayland-egl-Add-bcm_host-to-dependencies.patch \
    file://0012-interface-remove-faulty-assert-to-make-weston-happy-.patch \
    file://0013-zero-out-wl-buffers-in-egl_surface_free.patch \
    file://0014-initialize-front-back-wayland-buffers.patch \
    file://0015-Remove-RPC_FLUSH.patch \
"
S = "${WORKDIR}/git"

inherit cmake pkgconfig

EXTRA_OECMAKE = "-DCMAKE_BUILD_TYPE=Release -DCMAKE_EXE_LINKER_FLAGS='-Wl,--no-as-needed' \
                "

PACKAGECONFIG ?= "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'wayland', '', d)}"

PACKAGECONFIG[wayland] = "-DBUILD_WAYLAND=TRUE,,wayland,"

CFLAGS_append = " -fPIC"

do_install_append () {
	for f in `find ${D}${includedir}/interface/vcos/ -name "*.h"`; do
		sed -i 's/include "vcos_platform.h"/include "pthreads\/vcos_platform.h"/g' ${f}
		sed -i 's/include "vcos_futex_mutex.h"/include "pthreads\/vcos_futex_mutex.h"/g' ${f}
		sed -i 's/include "vcos_platform_types.h"/include "pthreads\/vcos_platform_types.h"/g' ${f}
	done
}

# Shared libs from userland package  build aren't versioned, so we need
# to force the .so files into the runtime package (and keep them
# out of -dev package).
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} += "dev-so"

FILES_${PN} += " \
    ${libdir}/*.so \
    ${libdir}/plugins"
FILES_${PN}-dev += "${includedir} \
                   ${prefix}/src"
FILES_${PN}-doc += "${datadir}/install"
FILES_${PN}-dbg += "${libdir}/plugins/.debug"

PACKAGE_ARCH = "${MACHINE_ARCH}"
