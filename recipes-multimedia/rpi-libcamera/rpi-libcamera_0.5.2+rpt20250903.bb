SUMMARY = "Raspberry PI libcamera fork"
SECTION = "libs"

LICENSE = "GPL-2.0-or-later & LGPL-2.1-or-later"

LIC_FILES_CHKSUM = "\
    file://LICENSES/GPL-2.0-or-later.txt;md5=fed54355545ffd980b814dab4a3b312c \
    file://LICENSES/LGPL-2.1-or-later.txt;md5=2a4f4fd2128ea2f65047ee63fbca9f68 \
"

SRC_URI = "git://github.com/raspberrypi/libcamera.git;protocol=https;branch=main"

PV = "0.5.2+rpt20250903+git${SRCPV}"
SRCREV = "bfd68f786964636b09f8122e6c09c230367390e7"

PE = "1"

DEPENDS = "\
    python3-pyyaml-native \
    python3-jinja2-native \
    python3-ply-native \
    python3-jinja2-native \
    udev \
    gnutls \
    chrpath-native \
    libevent \
    libyaml \
    \
    ${@bb.utils.contains('DISTRO_FEATURES', 'qt', 'qtbase qtbase-native', '', d)} \
"

PACKAGES =+ "${PN}-gst ${PN}-pycamera"

PACKAGECONFIG ??= "dng gst pycamera"
PACKAGECONFIG[dng] = ",,tiff"
PACKAGECONFIG[gst] = "-Dgstreamer=enabled,-Dgstreamer=disabled,gstreamer1.0 gstreamer1.0-plugins-base"
PACKAGECONFIG[pycamera] = "-Dpycamera=enabled,-Dpycamera=disabled,python3 python3-pybind11"
PACKAGECONFIG[pisp] = "-Dpipelines=rpi/pisp -Dipas=rpi/pisp, -Dpipelines=rpi/vc4 -Dipas=rpi/vc4, libpisp"

# machines which uses pisp
PACKAGECONFIG:append:raspberrypi5 = " pisp"

EXTRA_OEMESON = " \
    -Dv4l2=enabled \
    -Dcam=enabled \
    -Dlc-compliance=disabled \
    -Dtest=false \
    -Ddocumentation=disabled \
"

RDEPENDS:${PN} = "${@bb.utils.contains('DISTRO_FEATURES', 'wayland qt', 'qtwayland', '', d)}"

inherit meson pkgconfig python3native

do_configure:prepend() {
    sed -i -e 's|py_compile=True,||' ${S}/utils/codegen/ipc/mojo/public/tools/mojom/mojom/generate/template_expander.py
}

do_install:append() {
    chrpath -d ${D}${libdir}/libcamera.so
    chrpath -d ${D}${libexecdir}/libcamera/v4l2-compat.so
}

do_package:append() {
    bb.build.exec_func("do_package_recalculate_ipa_signatures", d)
}

do_package_recalculate_ipa_signatures() {
    local modules
    for module in $(find ${PKGD}/usr/lib/libcamera -name "*.so.sign"); do
        module="${module%.sign}"
        if [ -f "${module}" ] ; then
            modules="${modules} ${module}"
        fi
    done

    ${S}/src/ipa/ipa-sign-install.sh ${B}/src/ipa-priv-key.pem "${modules}"
}

FILES:${PN} += "\
    ${libexecdir}/libcamera/v4l2-compat.so \
    ${libdir}/libcamera/ipa/* \
    ${datadir}/libcamera/pipeline/rpi/* \
    ${datadir}/libcamera/ipa/rpi* \
"
FILES:${PN}-gst = "${libdir}/gstreamer-1.0"
FILES:${PN}-pycamera = "${PYTHON_SITEPACKAGES_DIR}/libcamera"

# libcamera-v4l2 explicitly sets _FILE_OFFSET_BITS=32 to get access to
# both 32 and 64 bit file APIs.
GLIBC_64BIT_TIME_FLAGS = ""
