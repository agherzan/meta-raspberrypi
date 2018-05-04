FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_rpi = " \
    file://xorg.conf.d/98-pitft.conf \
    file://xorg.conf.d/99-calibration.conf \
    file://xorg.conf.d/10-noglamor.conf \
"
do_install_append_rpi () {
    PITFT="${@bb.utils.contains("MACHINE_FEATURES", "pitft", "1", "0", d)}"
    VC4="${@bb.utils.contains("MACHINE_FEATURES", "vc4graphics", "1", "0", d)}"
    if [ "${PITFT}" = "1" ]; then
        install -d ${D}/${sysconfdir}/X11/xorg.conf.d/
        install -m 0644 ${WORKDIR}/xorg.conf.d/98-pitft.conf ${D}/${sysconfdir}/X11/xorg.conf.d/
        install -m 0644 ${WORKDIR}/xorg.conf.d/99-calibration.conf ${D}/${sysconfdir}/X11/xorg.conf.d/
    fi
    if [ "${VC4}" = "1" ]; then
        install -Dm 0644 ${WORKDIR}/xorg.conf.d/10-noglamor.conf ${D}/${sysconfdir}/X11/xorg.conf.d/10-noglamor.conf
    fi
}

FILES_${PN}_rpi += "${sysconfdir}/X11/xorg.conf ${sysconfdir}/X11/xorg.conf.d/*"
