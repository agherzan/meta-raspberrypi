require optee-examples.inc

SRCREV = "378dc0db2d5dd279f58a3b6cb3f78ffd6b165035"

COMPATIBLE_MACHINE = "^rpi$"

do_install:append(){
    install -d ${D}${base_libdir}/optee_armtz
    install -m 0444 ${B}/ta/* ${D}${base_libdir}/optee_armtz

    install -d ${D}${bindir}
    install -m 0744 ${B}/ta/* ${D}${bindir}

    install -d ${D}${libdir}/tee-supplicant/plugins
    install -m 0444 ${B}/plugins/* ${D}${libdir}/tee-supplicant/plugins
}

FILES:${PN} += " \
                ${base_libdir}/optee_armtz/* \
                ${bindir}/* \
                ${libdir}/tee-supplicant/plugins/* \
                "
