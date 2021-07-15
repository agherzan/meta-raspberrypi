SUMMARY = "Installation scripts and binaries for the Raspberry Pi 4 EEPROM"
DESCRIPTION = "This repository contains the rpi4 bootloader and scripts \
for updating it in the spi eeprom"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7dcd1a1eb18ae569857c21cae81347cb"

SRC_URI = " \
    git://github.com/raspberrypi/rpi-eeprom.git;protocol=git \
    file://0001-rpi-eeprom-config-convert-sheabang-from-python-to-py.patch \
"

S = "${WORKDIR}/git"

SRCREV = "09f77ad9fa8655a28de754640e82ca67aabe6c33"

RDEPENDS_${PN} += " \
    coreutils \
    python3 \
    ${@bb.utils.contains("MACHINE_FEATURES", "vc4graphics", "userlandtools", "userland", d)} \
"

PV = "0.0+git${SRCPV}"

inherit python3native

do_install() {
    install -d ${D}${bindir}

    # install executables
    install -m 0755 ${S}/firmware/vl805 ${D}${bindir}
    install -m 0755 ${S}/rpi-eeprom-update ${D}${bindir}
    install -m 0755 ${S}/rpi-eeprom-config ${D}${bindir}

    # copy firmware files
    install -d ${D}${base_libdir}/firmware/raspberrypi/bootloader/critical
    install -d ${D}${base_libdir}/firmware/raspberrypi/bootloader/stable
    install -d ${D}${base_libdir}/firmware/raspberrypi/bootloader/beta

    install -m 644 ${S}/firmware/critical/* ${D}${base_libdir}/firmware/raspberrypi/bootloader/critical
    install -m 644 ${S}/firmware/stable/* ${D}${base_libdir}/firmware/raspberrypi/bootloader/stable
    install -m 644 ${S}/firmware/beta/* ${D}${base_libdir}/firmware/raspberrypi/bootloader/beta

    # copy default config
    install -d ${D}${sysconfdir}/default
    install -D ${S}/rpi-eeprom-update-default ${D}${sysconfdir}/default/rpi-eeprom-update
}

FILES_${PN} += " ${sbindir}/vl805"
FILES_${PN} += "${base_libdir}/firmware/raspberrypi/bootloader/*"
FILES_${PN} += "${sysconfdir}/*"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

# vl805 tool sources are not available (yet), as it comes as a precompiled
# binary only. It has ARM architecture whereas target machine is Aarch64. We
# need to disable arch check for it otherwise it cannot packed.
QAPATHTEST[arch] = ""

COMPATIBLE_MACHINE = "raspberrypi4-64"
