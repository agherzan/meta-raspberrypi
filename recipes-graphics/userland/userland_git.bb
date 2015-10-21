DESCRIPTION = "This repository contains the source code for the ARM side \
libraries used on Raspberry Pi. These typically are installed in /opt/vc/lib \
and includes source for the ARM side code to interface to: EGL, mmal, GLESv2,\
vcos, openmaxil, vchiq_arm, bcm_host, WFC, OpenVG."
LICENSE = "Broadcom"
LIC_FILES_CHKSUM = "file://LICENCE;md5=957f6640d5e2d2acfce73a36a56cb32f"

PR = "r5"

PROVIDES = "virtual/libgles2 \
            virtual/egl"

COMPATIBLE_MACHINE = "raspberrypi"

SRCBRANCH = "master"
SRCFORK = "raspberrypi"
SRCREV = "40e377862410371a9962db79b81fd4f0f266430a"

SRC_URI = "\
    git://github.com/${SRCFORK}/userland.git;protocol=git;branch=${SRCBRANCH} \
    file://0001-fix-gcc-5.x-inlines.patch \
    file://0002-fix-musl-build.patch \
    file://0003-fix-alloc-size-uninitialized.patch \
    "

S = "${WORKDIR}/git"

inherit cmake

EXTRA_OECMAKE = "-DCMAKE_BUILD_TYPE=Release -DCMAKE_EXE_LINKER_FLAGS='-Wl,--no-as-needed'"
CFLAGS_append = " -fPIC"

# The compiled binaries don't provide sonames.
SOLIBS = "${SOLIBSDEV}"

do_install_append() {
    mkdir -p ${D}/${prefix}
    mv ${D}/opt/vc/* ${D}/${prefix}
    rm -rf ${D}/opt
}

FILES_${PN} += " \
    ${libdir}/*${SOLIBS} \
    ${libdir}/plugins"
FILES_${PN}-dev = "${includedir} \
                   ${prefix}/src"
FILES_${PN}-doc += "${datadir}/install"
FILES_${PN}-dbg += "${libdir}/plugins/.debug"

PACKAGE_ARCH = "${MACHINE_ARCH}"

