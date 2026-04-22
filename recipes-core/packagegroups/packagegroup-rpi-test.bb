DESCRIPTION = "RaspberryPi Test Packagegroup"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

COMPATIBLE_MACHINE = "^rpi$"

OMXPLAYER  = "${@bb.utils.contains('MACHINE_FEATURES', 'vc4graphics', '', 'omxplayer', d)}"

RDEPENDS:${PN} = "\
    optee-os \
    optee-test \
    optee-client \
    optee-examples \
    fscryptctl \
    keyutils \
    e2fsprogs-tune2fs \
"
