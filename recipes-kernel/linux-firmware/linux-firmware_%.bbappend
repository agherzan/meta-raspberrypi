FILESEXTRAPATHS_prepend_rpi := "${THISDIR}/files:"

SRC_URI_append_rpi = " \
    file://brcmfmac43430-sdio.bin \
    file://brcmfmac43430-sdio.txt \
"

do_install_append_rpi() {
    # Overwrite v7.45.41.26 by the one we currently provide in this layer
    # (v7.45.41.46)
    local _firmware="brcmfmac43430-sdio.bin"
    local _oldmd5=9258986488eca9fe5343b0d6fe040f8e
    if [ "$(md5sum ${D}${nonarch_base_libdir}/firmware/brcm/$_firmware | awk '{print $1}')" != "$_oldmd5" ]; then
        _firmware=""
        bbwarn "linux-firmware stopped providing brcmfmac43430 v7.45.41.26."
    else
        _firmware="${WORKDIR}/$_firmware"
    fi

    mkdir -p ${D}/${nonarch_base_libdir}/firmware/brcm
    install -m 0644 $_firmware ${WORKDIR}/brcmfmac43430-sdio.txt ${D}${nonarch_base_libdir}/firmware/brcm
}

FILES_${PN}-bcm43430_append_rpi = " \
    ${nonarch_base_libdir}/firmware/brcm/brcmfmac43430-sdio.txt \
"
