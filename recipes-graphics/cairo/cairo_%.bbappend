PACKAGECONFIG_GLESV2 = " ${@bb.utils.contains('DISTRO_FEATURES', 'x11', '', 'glesv2', d)}"

PACKAGECONFIG_append_rpi = "${@bb.utils.contains('MACHINE_FEATURES', 'vc4graphics', '', ' ${PACKAGECONFIG_GLESV2}', d)}"
