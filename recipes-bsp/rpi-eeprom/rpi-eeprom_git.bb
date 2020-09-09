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

COMPATIBLE_MACHINE = "raspberrypi4-64"

DEPENDS += "python3-native"
RDEPENDS_${PN} += "coreutils python3 userland"

SRCBRANCH = "master"
SRCFORK = "raspberrypi"
SRCREV = "09f77ad9fa8655a28de754640e82ca67aabe6c33"

# Use the date of the above commit as the package version. Update this when
# SRCREV is changed.
PV = "20200903"

S = "${WORKDIR}/git"

inherit deploy python3native

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
