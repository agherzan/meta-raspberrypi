FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://qt5base-rpi-platform.patch"

PACKAGECONFIG_GL = "gles2"
PACKAGECONFIG += "openvg"
