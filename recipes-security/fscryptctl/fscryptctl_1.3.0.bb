SUMMARY = "fscryptctl - Linux filesystem encryption utility"
DESCRIPTION = "Command-line tool for managing Linux filesystem encryption"
HOMEPAGE = "https://github.com/google/fscryptctl"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI = "git://github.com/google/fscryptctl.git;protocol=https;branch=master"
SRCREV = "f1ec919877f6b5360c03fdb44b6ed8a47aa459e8"

SRC_URI += "file://0001-support-trusted-keys-TA-access-from-userspace.patch"

S = "${WORKDIR}/git"

DEPENDS = "libcap-ng"
inherit pkgconfig

DEPENDS += "optee-client"
RDEPENDS:${PN} += "optee-client"

EXTRA_OEMAKE += "CFG_TRUSTED_KEYS=y TEEC_EXPORT=${STAGING_DIR_HOST}${prefix}"

do_compile() {
    oe_runmake fscryptctl
}

do_install() {
    oe_runmake install-bin DESTDIR=${D} BINDIR=${bindir}
}

FILES:${PN} = "${bindir}/fscryptctl"
COMPATIBLE_MACHINE = "^rpi$"
