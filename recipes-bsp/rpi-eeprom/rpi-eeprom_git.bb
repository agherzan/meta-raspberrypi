FILESEXTRAPATHS_append := ":${THISDIR}/files"

DESCRIPTION = "This repository contains the rpi4 bootloader and scripts \
for updating it in the spi eeprom"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7dcd1a1eb18ae569857c21cae81347cb"

# EEPROM configuration default values for this version, as specified at
# https://www.raspberrypi.org/documentation/hardware/raspberrypi/booteeprom.md
SRC_URI = " \
    git://github.com/${SRCFORK}/rpi-eeprom.git;protocol=git;branch=${SRCBRANCH} \
    file://default-config.txt \
    file://0001-rpi-eeprom-config-use-usr-bin-python.patch \
"
# file://0001-rpi-eeprom-config-use-usr-bin-python.patch

COMPATIBLE_MACHINE = "raspberrypi4-64"

DEPENDS += "python3-native"
RDEPENDS_${PN} += "coreutils python3 userland"

SRCBRANCH = "master"
SRCFORK = "raspberrypi"
SRCREV = "09f77ad9fa8655a28de754640e82ca67aabe6c33"

inherit deploy python3native

# Use the date of the above commit as the package version. Update this when
# SRCREV is changed.
PV = "20200903"

# We use the latest stable version
# which is available in "critical"
LATEST_STABLE_PIEEPROM_FW = "2020-04-16"

S = "${WORKDIR}/git"

VL805_FW_REV = "000137ad"

# default-config.txt contains the default options
# for this fw release, and provides a way for altering
# the configuration that exists in the binary
do_compile() {
    cd ${WORKDIR} && cp ${S}/rpi-eeprom-config .
    echo $(which python)
    $(which python) ./rpi-eeprom-config ${S}/firmware/critical/pieeprom-${LATEST_STABLE_PIEEPROM_FW}.bin \
        --config ./default-config.txt \
        --out ./pieeprom-latest-stable.bin
}

do_deploy () {
    if [ -d ${DEPLOY_DIR_IMAGE}/rpi-eeprom ]; then
        rm -rf ${DEPLOY_DIR_IMAGE}/rpi-eeprom
    fi
    mkdir ${DEPLOY_DIR_IMAGE}/rpi-eeprom/

    cp "${WORKDIR}/pieeprom-latest-stable.bin" ${DEPLOY_DIR_IMAGE}/rpi-eeprom/pieeprom-latest-stable.bin

    if [ -z ${VL805_FW_REV} ]; then
        bberror "Unknown version for vl805 firmware!"
    fi

    cp ${S}/firmware/critical/vl805-${VL805_FW_REV}.bin ${DEPLOY_DIR_IMAGE}/${PN}/vl805-latest-stable.bin
}

do_install() {
    install -d ${D}${bindir}
    
    # install executables
    install -m 0755 ${S}/firmware/vl805 ${D}${bindir}
    install -m 0755 ${S}/rpi-eeprom-update ${D}${bindir}
    install -m 0755 ${S}/rpi-eeprom-config ${D}${bindir}

    # copy firmware files
    # install -d ${D}${base_libdir}/firmware/raspberrypi/bootloader
    install -d ${D}${base_libdir}/firmware/raspberrypi/bootloader/critical
    install -d ${D}${base_libdir}/firmware/raspberrypi/bootloader/stable
    install -d ${D}${base_libdir}/firmware/raspberrypi/bootloader/beta
    # install -m 644 ${S}/firmware/* ${D}${base_libdir}/firmware/raspberrypi/bootloader

    install -m 644 ${S}/firmware/critical/* ${D}${base_libdir}/firmware/raspberrypi/bootloader/critical
    install -m 644 ${S}/firmware/stable/* ${D}${base_libdir}/firmware/raspberrypi/bootloader/stable
    install -m 644 ${S}/firmware/beta/* ${D}${base_libdir}/firmware/raspberrypi/bootloader/beta

    # copy default config
    # install -d ${D}${sysconfdir}/default/rpi-eeprom-update
    install -d ${D}${sysconfdir}/default
    install -D ${S}/rpi-eeprom-update-default ${D}${sysconfdir}/default/rpi-eeprom-update
}

FILES_${PN} += " ${sbindir}/vl805"
FILES_${PN} += "${base_libdir}/firmware/raspberrypi/bootloader/*"
FILES_${PN} += "${sysconfdir}/*"


addtask do_deploy before do_package after do_compile

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

# Ensure binaries are really deployed
# on each build
do_deploy[nostamp] = "1"

# vl805 tool sources are not available (yet),
# as it comes as a precompiled binary only.
# It has ARM architecture whereas target machine
# is Aarch64. We need to disable arch check for it
# otherwise it cannot packed.
QAPATHTEST[arch] = ""
