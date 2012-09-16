DESCRIPTION = "Commented config.txt file for the Raspberry Pi. \
               The Raspberry Pi config.txt file is read by the GPU before \
               the ARM core is initialised. It can be used to set various \
               system configuration parameters."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

COMPATIBLE_MACHINE = "raspberrypi"

SRCREV = "23e90347da3f1a6029984306e7eb5177adc34df8"
SRC_URI = "git://github.com/Evilpaul/RPi-config.git;protocol=git;branch=master \
          "

S = "${WORKDIR}/git"

PR = "r0"

addtask deploy before do_package after do_install

do_deploy() {
	install -d ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles
	cp config.txt ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/
	if [ -n "${KEY_DECODE_MPG2}" ]; then
		sed -i '/#decode_MPG2/ c\decode_MPG2=${KEY_DECODE_MPG2}' ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/config.txt
	fi
	if [ -n "${KEY_DECODE_WVC1}" ]; then
		sed -i '/#decode_WVC1/ c\decode_MVC1=${KEY_DECODE_WVC1}' ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/config.txt
	fi
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
