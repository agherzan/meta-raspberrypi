FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_raspberrypi = " file://xorg.conf.d/10-evdev.conf "

do_install_append_raspberrypi () {
	install -d ${D}/${sysconfdir}/X11/xorg.conf.d/
	install -m 0644 ${WORKDIR}/xorg.conf.d/* ${D}/${sysconfdir}/X11/xorg.conf.d/
}

FILES_${PN}_raspberrypi += "${sysconfdir}/X11/xorg.conf ${sysconfdir}/X11/xorg.conf.d/*"
