DESCRIPTION = "Closed source binary files to help boot the ARM on the BCM2835."
LICENSE = "Proprietary"

LIC_FILES_CHKSUM = "file://LICENCE.broadcom;md5=e86e693d19572ee64cc8b17fb062faa9"

include ../common/firmware.inc

SRC_URI = " \
        git://github.com/raspberrypi/firmware.git;protocol=git;branch=master  \
"

S = "${WORKDIR}/git/boot"

PR = "r1"

addtask deploy before do_package after do_install

do_deploy() {
	install -d ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles
	for i in *.elf ; do
		cp $i ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles
	done
	for i in *.bin ; do
		cp $i ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles
	done
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
