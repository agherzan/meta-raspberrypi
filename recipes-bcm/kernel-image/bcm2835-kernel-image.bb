DESCRIPTION = "Helper recipe to make the munged kernel.img image for the RaspberryPi."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

PR = "${MACHINE_KERNEL_PR}.0"
DEPENDS = "bcm2835-bootfiles bcm2835-mkimage-native"

S = "${WORKDIR}"

addtask deploy before do_package after do_install

do_deploy() {
	install -d ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles
	bcm2835-mkimage.py ${DEPLOY_DIR_IMAGE}/zImage-${MACHINE}.bin ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/kernel.img ${STAGING_BINDIR_NATIVE}/
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
