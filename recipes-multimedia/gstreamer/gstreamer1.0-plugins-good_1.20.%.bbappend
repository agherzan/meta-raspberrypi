FILESEXTRAPATHS:prepend:rpi := "${THISDIR}/${PN}:"

SRC_URI:append:rpi = " \
    file://0001-fix-v4l2-bt709.patch \
"

PACKAGECONFIG:append:rpi = "${@bb.utils.contains('MACHINE_FEATURES', 'vc4graphics', '', ' rpi', d)}"
