DESCRIPTION = "Commented config.txt file for the Raspberry Pi. \
               The Raspberry Pi config.txt file is read by the GPU before \
               the ARM core is initialised. It can be used to set various \
               system configuration parameters."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

COMPATIBLE_MACHINE = "raspberrypi"

SRCREV = "afeaed02ba5f01298dec3ac4e1bd98f27bcd876e"
SRC_URI = "git://github.com/Evilpaul/RPi-config.git;protocol=git;branch=master \
          "

S = "${WORKDIR}/git"

PR = "r1"

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
	if [ -n "${DISABLE_OVERSCAN}" ]; then
		sed -i '/#disable_overscan/ c\disable_overscan=${DISABLE_OVERSCAN}' ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/config.txt
	fi
	if [ -n "${ARM_FREQ}" ]; then
		sed -i '/#arm_freq/ c\arm_freq=${ARM_FREQ}' ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/config.txt
	fi
	if [ -n "${CORE_FREQ}" ]; then
		sed -i '/#core_freq/ c\core_freq=${CORE_FREQ}' ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/config.txt
	fi
	if [ -n "${SDRAM_FREQ}" ]; then
		sed -i '/#sdram_freq/ c\sdram_freq=${SDRAM_FREQ}' ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/config.txt
	fi
	if [ -n "${OVER_VOLTAGE}" ]; then
		sed -i '/#over_voltage/ c\over_voltage=${OVER_VOLTAGE}' ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/config.txt
	fi
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
