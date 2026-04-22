require optee-test.inc

SRCREV = "695231ef8987866663a9ed5afd8f77d1bae3dc08"

COMPATIBLE_MACHINE = "^rpi$"

# Include ffa_spmc test group if the SPMC test is enabled.
# Supported after op-tee v3.20
EXTRA_OEMAKE:append = "${@bb.utils.contains('MACHINE_FEATURES', 'optee-spmc-test', \
                                        ' CFG_SPMC_TESTS=y CFG_SECURE_PARTITION=y', '' , d)}"

RDEPENDS:${PN} += "${@bb.utils.contains('MACHINE_FEATURES', 'optee-spmc-test', \
                                              ' arm-ffa-user', '' , d)}"


do_install:append(){
    install -d ${D}${bindir}
    install -m 0755 ${B}/xtest/xtest ${D}${bindir}/xtest

    install -d ${D}${base_libdir}/optee_armtz
    install -m 0444 ${B}/ta/*/*.ta ${D}${base_libdir}/optee_armtz

    install -d ${D}${libdir}/tee-supplicant/plugins
    install -m 0444 ${B}/supp_plugin/*.plugin ${D}${libdir}/tee-supplicant/plugins/
}

FILES:${PN} += " \
                ${bindir}/xtest \
                ${base_libdir}/optee_armtz/* \
                ${libdir}/tee-supplicant/plugins/* \
                "