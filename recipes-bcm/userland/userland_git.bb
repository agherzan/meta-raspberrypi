DESCRIPTION = "This repository contains the source code for the ARM side \
libraries used on Raspberry Pi. These typically are installed in /opt/vc/lib \
and includes source for the ARM side code to interface to: EGL, mmal, GLESv2,\
vcos, openmaxil, vchiq_arm, bcm_host, WFC, OpenVG."
LICENSE = "Broadcom"
LIC_FILES_CHKSUM = "file://LICENCE;md5=957f6640d5e2d2acfce73a36a56cb32f"

PR = "r0"

PROVIDES = "virtual/libgles2 \
            virtual/egl"
COMPATIBLE_MACHINE = "raspberrypi"

SRCREV = "ef62d33406ee01864d58b80f6d0c0355ed86aaa1"
SRC_URI = "git://github.com/raspberrypi/userland.git;protocol=git;branch=master \
           file://install_vmcs \
          "
S = "${WORKDIR}/git"

inherit cmake

EXTRA_OECMAKE = " \
                 -DCMAKE_BUILD_TYPE=Release \
                "

do_configure_prepend () {
    sed -i "/10-vchiq.rules/d" ${S}/interface/vchiq_arm/CMakeLists.txt
    mkdir -p makefiles/cmake/scripts
    cp ${WORKDIR}/install_vmcs ${S}/makefiles/cmake/scripts
}

FILES_${PN} = "/opt/vc/lib/*.so \
               /opt/vc/sbin \
               /opt/vc/bin \
               /etc \
              "
FILES_${PN}-dbg += "/opt/vc/lib/.debug \
                   /opt/vc/sbin/.debug \
                   /opt/vc/bin/.debug \
                  "
FILES_${PN}-dev = "/opt/vc/include/"
FILES_${PN}-staticdev = "/opt/vc/lib/*.a"
FILES_${PN}-doc = "/opt/vc/share/"