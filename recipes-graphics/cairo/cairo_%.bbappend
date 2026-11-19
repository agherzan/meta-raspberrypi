PACKAGECONFIG_GLESV2 = " ${@bb.utils.contains('DISTRO_FEATURES', 'x11', '', 'glesv2', d)}"
