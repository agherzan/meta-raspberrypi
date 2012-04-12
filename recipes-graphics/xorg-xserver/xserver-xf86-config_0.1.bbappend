FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
# Don't forget to bump PRINC if you update the extra files.
PRINC = "4"

THISDIR := "${@os.path.dirname(bb.data.getVar('FILE', d, True))}"
FILESPATH =. "${@base_set_filespath(["${THISDIR}/${PN}"], d)}:"

SRC_URI_append_raspberrypi = " file://xorg.conf.d/*"

CONFFILES_${PN} += "${sysconfdir}/X11/xorg.conf.d/*"

do_install_append () {
	install -d ${D}/${sysconfdir}/X11/xorg.conf.d/
	install -m 0644 ${WORKDIR}/xorg.conf.d/* ${D}/${sysconfdir}/X11/xorg.conf.d/
}

FILES_${PN} += "${sysconfdir}/X11/xorg.conf.d"