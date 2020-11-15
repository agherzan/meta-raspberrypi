FILESEXTRAPATHS_prepend_rpi := "${THISDIR}/${PN}:"

SRC_URI_append_rpi = " file://0001-rpicamsrc-add-vchostif-library-as-it-is-required-to-.patch"

EXTRA_OEMESON_remove_rpi = "-Drpicamsrc=disabled"
PACKAGECONFIG[rpi] = "-Drpicamsrc=enabled,-Drpicamsrc=disabled,userland"

PACKAGECONFIG_append_rpi = "${@bb.utils.contains('MACHINE_FEATURES', 'vc4graphics', '', ' rpi', d)}"
