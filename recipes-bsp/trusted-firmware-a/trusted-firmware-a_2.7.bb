require trusted-firmware-a.inc
DEPENDS += " coreutils-native"

# FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
# SRC_URI:append = " file://0001-rpi3-pass-DTB-address-to-BL32-OP-TEE.patch"
# TF-A v2.7
SRCREV_tfa = "35f4c7295bafeb32c8bcbdfb6a3f2e74a57e732b"

LIC_FILES_CHKSUM += "file://docs/license.rst;md5=b2c740efedc159745b9b31f88ff03dde"

# mbed TLS v2.28.0
SRC_URI_MBEDTLS = "git://github.com/ARMmbed/mbedtls.git;name=mbedtls;protocol=https;destsuffix=git/mbedtls;branch=mbedtls-2.28"
SRCREV_mbedtls = "8b3f26a5ac38d4fdccbc5c5366229f3e01dafcc0"

LIC_FILES_CHKSUM_MBEDTLS = "file://mbedtls/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"


COMPATIBLE_MACHINE = "^rpi$"
TFA_PLATFORM = "rpi3"
TFA_DEBUG = "1"
TFA_UBOOT = "1"
TFA_BUILD_TARGET = "all fip"
TFA_INSTALL_TARGET = "bl1 bl2 bl31 dtbs fip"
TFA_SPD = "opteed"

# BL1 loads BL32 (optee). So, optee needs to be built first:
DEPENDS += "optee-os u-boot"


EXTRA_OEMAKE += " \
                    PLAT=rpi3 \
                    NEED_BL32=yes \
                    BL32=${RECIPE_SYSROOT}/lib/firmware/tee-header_v2.bin \
                    BL32_EXTRA1=${RECIPE_SYSROOT}/lib/firmware/tee-pager_v2.bin \
                    BL32_EXTRA2=${RECIPE_SYSROOT}/lib/firmware/tee-pageable_v2.bin \
                    BL33=${RECIPE_SYSROOT}/lib/firmware/u-boot.bin \
                    LOG_LEVEL=40 \
                    CRASH_REPORTING=1 \
                    RPI3_PRELOADED_DTB_BASE=0x01000000 \
                    "

do_compile:prepend() {
    sed -i '/^LDLIBS/ s@$@ ${BUILD_LDFLAGS}@' ${S}/tools/fiptool/Makefile
}

do_compile(){
    oe_runmake -C ${S}
}

do_deploy[depends] += "optee-os:do_deploy"

do_deploy(){
    install -d ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}
    cp ${B}/rpi3/debug/armstub8.bin ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}
}

addtask deploy before do_build after do_install
do_deploy[dirs] += "${DEPLOYDIR}/${BOOTFILES_DIR_NAME}"
