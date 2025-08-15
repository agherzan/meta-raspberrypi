SUMMARY = "U-boot boot scripts for Raspberry Pi"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
COMPATIBLE_MACHINE = "^rpi$"

DEPENDS = "u-boot-mkimage-native"

INHIBIT_DEFAULT_DEPS = "1"

SRC_URI = "file://boot.cmd.in \
           file://ab.boot.cmd.in"

BOOT_MEDIA ?= "mmc"

do_compile() {
    if [ "${RPI_AB_PARTITION_LAYOUT}" = "0" ]; then
        sed -e 's/@@KERNEL_IMAGETYPE@@/${KERNEL_IMAGETYPE}/' \
            -e 's/@@KERNEL_BOOTCMD@@/${KERNEL_BOOTCMD}/' \
            -e 's/@@BOOT_MEDIA@@/${BOOT_MEDIA}/' \
            "${WORKDIR}/boot.cmd.in" > "${WORKDIR}/boot.cmd"
        mkimage -A ${UBOOT_ARCH} -T script -C none -n "Boot script" -d "${WORKDIR}/boot.cmd" boot.scr
    else
        sed -e 's/@@KERNEL_IMAGETYPE@@/${KERNEL_IMAGETYPE}/' \
            -e 's/@@KERNEL_BOOTCMD@@/${KERNEL_BOOTCMD}/' \
            -e 's/@@BOOT_MEDIA@@/${BOOT_MEDIA}/' \
            -e 's/@@RPI_AB_PARTITION_LAYOUT@@/${RPI_AB_PARTITION_LAYOUT}/' \
            "${WORKDIR}/ab.boot.cmd.in" > "${WORKDIR}/boot.cmd"
        mkimage -A ${UBOOT_ARCH} -T script -C none -n "Boot script" -d "${WORKDIR}/boot.cmd" boot.scr
    fi
}

inherit kernel-arch deploy nopackages

do_deploy() {
    install -d ${DEPLOYDIR}
    install -m 0644 boot.scr ${DEPLOYDIR}
}

addtask do_deploy after do_compile before do_build

PROVIDES += "u-boot-default-script"
