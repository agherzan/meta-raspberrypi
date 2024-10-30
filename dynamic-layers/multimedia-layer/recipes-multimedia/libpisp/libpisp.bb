SUMMARY = "Linux libcamera framework"
SECTION = "libs"

LICENSE = "GPL-2.0-or-later & LGPL-2.1-or-later"

LIC_FILES_CHKSUM = "\
    file://LICENSES/GPL-2.0-only.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
"

SRC_URI = " \
        git://github.com/raspberrypi/libpisp.git;protocol=https;branch=main \
"

SRCREV = "6e3a53d137f47bf359801a30b58e47efd3c7b8a3"

PE = "1"

S = "${WORKDIR}/git"

DEPENDS = "nlohmann-json"
#DEPENDS += "${@bb.utils.contains('DISTRO_FEATURES', 'qt', 'qtbase qtbase-native', '', d)}"

PACKAGES =+ "${PN}-gst"

#PACKAGECONFIG ??= ""
#PACKAGECONFIG[gst] = "-Dgstreamer=enabled,-Dgstreamer=disabled,gstreamer1.0 gstreamer1.0-plugins-base"

#LIBCAMERA_PIPELINES ??= "auto"

EXTRA_OEMESON = " \
		"

RDEPENDS:${PN} = "${@bb.utils.contains('DISTRO_FEATURES', 'wayland qt', 'qtwayland', '', d)}"

inherit meson pkgconfig python3native

do_configure:prepend() {
    #sed -i -e 's|py_compile=True,||' ${S}/utils/ipc/mojo/public/tools/mojom/mojom/generate/template_expander.py
}

do_install:append() {
    #chrpath -d ${D}${libdir}/libcamera.so
    #chrpath -d ${D}${libexecdir}/libcamera/v4l2-compat.so
}


# libcamera-v4l2 explicitly sets _FILE_OFFSET_BITS=32 to get access to
# both 32 and 64 bit file APIs.
GLIBC_64BIT_TIME_FLAGS = ""
