DESCRIPTION = "ssh file for the Raspberry Pi. \
               by adding this file we enable ssh connections."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

COMPATIBLE_MACHINE = "^rpi$"

SRCREV = "648ffc470824c43eb0d16c485f4c24816b32cd6f"
SRC_URI = "git://github.com/Evilpaul/RPi-config.git;protocol=https;branch=master \
          "

S = "${WORKDIR}/git"

PR = "r5"

inherit deploy nopackages

do_deploy() {
    install -d ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}

    # Create the empty ssh file to allow for ssh connections
    if ([ "${ENABLE_SSH}" = "1" ]); then
        touch ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/ssh
    fi
}

addtask deploy before do_build after do_install
do_deploy[dirs] += "${DEPLOYDIR}/${BOOTFILES_DIR_NAME}"

PACKAGE_ARCH = "${MACHINE_ARCH}"
