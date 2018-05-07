SUMMARY = "Firmware files for Bluetooth support for Raspberry Pi"
HOMEPAGE = "https://github.com/RPi-Distro/bluez-firmware"
SECTION = "kernel"
LICENSE = "cypress-wireless"
NO_GENERIC_LICENSE[cypress-wireless] = "LICENCE.cypress"
LIC_FILES_CHKSUM = "\
    file://LICENCE.cypress;md5=cbc5f665d04f741f1e006d2096236ba7 \
    file://debian/copyright;md5=1c734a245a410aa1f9eebcb2e9b62105 \
"

SRC_URI = "\
    git://github.com/RPi-Distro/bluez-firmware \
    file://LICENCE.cypress \
"
SRCREV = "e28cd7ee8615de33aa7ec2b41d556af61a4a2707"
UPSTREAM_VERSION_UNKNOWN = "1"

S = "${WORKDIR}/git"

inherit allarch

python do_populate_lic_prepend() {
    # Put the license into the source where license.bbclass wants it
    import shutil
    workdir = d.getVar('WORKDIR')
    srcdir = d.getVar('S')
    shutil.copy(os.path.join(workdir, 'LICENCE.cypress'), srcdir)
}

do_install() {
    install -d ${D}${nonarch_base_libdir}/firmware/brcm/
    install -m 0644 ${S}/broadcom/BCM4345C0.hcd ${D}${nonarch_base_libdir}/firmware/brcm
    install -m 0644 ${S}/broadcom/BCM43430A1.hcd ${D}${nonarch_base_libdir}/firmware/brcm
    install -m 0644 ${WORKDIR}/LICENCE.cypress ${D}${nonarch_base_libdir}/firmware
}

FILES_${PN} = "\
    ${nonarch_base_libdir}/firmware \
"

# Firmware files are generally not ran on the CPU, so they can be
# allarch despite being architecture specific
INSANE_SKIP = "arch"
