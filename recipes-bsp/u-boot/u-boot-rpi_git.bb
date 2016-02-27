DESCRIPTION = "U-Boot port for RaspberryPi"

require recipes-bsp/u-boot/u-boot.inc

DEPENDS += "rpi-mkimage-native"

PROVIDES += "u-boot"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=1707d6db1d42237583f50183a5651ecb"

SRCREV = "6709570cdc947c2a546f96d571551acf4474778c"
SRC_URI = "git://github.com/gonzoua/u-boot-pi.git;branch=rpi"

S = "${WORKDIR}/git"

UBOOT_MACHINE = "rpi_b"
UBOOT_MAKE_TARGET = "u-boot.bin"
UBOOT_SUFFIX = "img"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "raspberrypi"

do_compile_append() {
    # Create kernel.img from uboot.bin and name it u-boot.img
    ${STAGING_LIBEXECDIR_NATIVE}/imagetool-uncompressed.py u-boot.bin
    mv kernel.img u-boot.img
}
