DESCRIPTION = "This repository contains the source code for the ARM side \
libraries used on Raspberry Pi. These typically are installed in /opt/vc/lib \
and includes source for the ARM side code to interface to: EGL, mmal, GLESv2,\
vcos, openmaxil, vchiq_arm, bcm_host, WFC, OpenVG."
LICENSE = "Broadcom"
LIC_FILES_CHKSUM = "file://LICENCE;md5=957f6640d5e2d2acfce73a36a56cb32f"

PR = "r6"

PROVIDES = "virtual/libgles2 \
            virtual/egl \
            virtual/wayland-egl"
COMPATIBLE_MACHINE = "raspberrypi"

SRCBRANCH = "master"
SRCFORK = "raspberrypi"
SRCREV = "b864a841e5a459a66a890c22b3a34127cd226238"

SRC_URI = "git://github.com/${SRCFORK}/userland.git;protocol=git;branch=${SRCBRANCH} \
           file://bcm_host.pc \
           file://egl.pc \
           file://glesv2.pc \
           file://0001-Use-newer-POSIX-macro.patch \
           file://0001-Allow-applications-to-set-next-resource-handle.patch \
           file://0002-wayland-Add-support-for-the-Wayland-winsys.patch \
           file://0004-Fix-include-path.patch \
           file://0006-fix-wayland-egl-dependencies-not-found.patch \
"

S = "${WORKDIR}/git"
B="${S}"

inherit cmake

EXTRA_OECMAKE = "-DCMAKE_BUILD_TYPE=Release -DCMAKE_EXE_LINKER_FLAGS='-Wl,--no-as-needed'"
EXTRA_OECMAKE += "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', '-DBUILD_WAYLAND=1', '', d)}"
DEPENDS += "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'wayland', '', d)}"

CFLAGS_append = " -fPIC"

# The compiled binaries don't provide sonames.
SOLIBS = "${SOLIBSDEV}"

do_install_append() {
    mkdir -p ${D}/${prefix}
    mv ${D}/opt/vc/* ${D}/${prefix}
    rm -rf ${D}/opt

    # patch include files to find vcos_platform_types.h
    find ${D}/${prefix}/include/interface -type f -exec sed -i 's/vcos_platform.h/pthreads\/vcos_platform.h/g' {} \;
    find ${D}/${prefix}/include/interface -type f -exec sed -i 's/vcos_platform_types.h/pthreads\/vcos_platform_types.h/g' {} \;

    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/egl.pc ${D}${libdir}/pkgconfig/
    install -m 0644 ${WORKDIR}/bcm_host.pc ${D}${libdir}/pkgconfig/
    install -m 0644 ${WORKDIR}/glesv2.pc ${D}${libdir}/pkgconfig/
}

FILES_${PN} += " \
    ${libdir}/*${SOLIBS} \
    ${libdir}/*.so.* \
    ${libdir}/plugins"
FILES_${PN}-dev = "${includedir} \
                   ${prefix}/src \
                   ${libdir}/pkgconfig/*.pc"
FILES_${PN}-doc += "${datadir}/install"
FILES_${PN}-dbg += "${libdir}/plugins/.debug"

PACKAGE_ARCH = "${MACHINE_ARCH}"

# Disable qa check for dev-so as main package installs libEGL.so
INSANE_SKIP_${PN} = "dev-so"
