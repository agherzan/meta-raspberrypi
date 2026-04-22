FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append:rpi = " \
    file://fw_env.config \
    file://u-boot_rpi3_env \
"

SRC_URI:append:rpi = " \
                        file://0001-rpi-always-set-fdt_addr-with-firmware-provided-FDT-address.patch \
                        file://myconfig.cfg \
                    "

DEPENDS:append:rpi = " u-boot-default-script"

do_compile:append:rpi(){
    ${B}/tools/mkenvimage -s 0x4000 -o ${B}/uboot.env ${WORKDIR}/u-boot_rpi3_env
}

do_install:append:rpi () {
    install -d ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/fw_env.config ${D}${sysconfdir}/fw_env.config

    install -d 644 ${D}${nonarch_base_libdir}/firmware
    install -m 0644 ${B}/u-boot.bin ${D}${nonarch_base_libdir}/firmware/u-boot.bin
}

do_deploy:append:rpi(){
    install -d ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}
    cp ${B}/uboot.env  ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/uboot.env
}

FILES:${PN}:append:rpi = " ${nonarch_base_libdir}/firmware/u-boot.bin"
# Temporary avoid Raspberry Pi 5 because U-Boot has not been ported yet
COMPATIBLE_MACHINE:raspberrypi5 = "(-)"
