require userland.inc

RCONFLICTS_${PN} = "userland"

# Keep only those libs & bins that are actually used during EEPROM update
do_install_append () {
    # move out the files we need  to a temproary location
    install -d ${D}/../mvtmp${bindir} ${D}/../mvtmp${libdir}
    mv ${D}${bindir}/dtoverlay ${D}${bindir}/vcgencmd \
        ${D}${bindir}/vcmailbox ${D}/../mvtmp${bindir}
    mv ${D}${libdir}/libdtovl.so ${D}${libdir}/libvchiq_arm.so \
        ${D}${libdir}/libvcos.so ${D}/../mvtmp${libdir}

    # remove everything within the image directory
    rm -rf ${D}/*

    # install back only the selected files
    install -d ${D}${bindir} ${D}${libdir}
    install -m 0755 ${D}/../mvtmp${bindir}/dtoverlay ${D}${bindir}/
    install -m 0755 ${D}/../mvtmp${bindir}/vcgencmd ${D}${bindir}/
    install -m 0755 ${D}/../mvtmp${bindir}/vcmailbox ${D}${bindir}/
    ln -s dtoverlay ${D}${bindir}/dtparam
    install -m 0644 ${D}/../mvtmp${libdir}/libdtovl.so ${D}${libdir}/
    install -m 0644 ${D}/../mvtmp${libdir}/libvchiq_arm.so ${D}${libdir}/
    install -m 0644 ${D}/../mvtmp${libdir}/libvcos.so ${D}${libdir}/

    # cleanup temporary directory
    rm -rf ${D}/../mvtmp
}

FILES_${PN} += "${libdir}/*.so"

COMPATIBLE_MACHINE = "raspberrypi4-64"
