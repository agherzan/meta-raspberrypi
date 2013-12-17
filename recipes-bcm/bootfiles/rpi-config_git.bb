DESCRIPTION = "Commented config.txt file for the Raspberry Pi. \
               The Raspberry Pi config.txt file is read by the GPU before \
               the ARM core is initialised. It can be used to set various \
               system configuration parameters."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

COMPATIBLE_MACHINE = "raspberrypi"

SRCREV = "5d2ca5f9bcb1b239c051e20c05a233fd79cf09d5"
SRC_URI = "git://github.com/Evilpaul/RPi-config.git;protocol=git;branch=master \
          "

S = "${WORKDIR}/git"

PR = "r4"

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

	# GPU memory
	if [ -n "${GPU_MEM}" ]; then
		sed -i '/#gpu_mem=/ c\gpu_mem=${GPU_MEM}' ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/config.txt
	fi
	if [ -n "${GPU_MEM_256}" ]; then
		sed -i '/#gpu_mem_256/ c\gpu_mem_256=${GPU_MEM_256}' ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/config.txt
	fi
	if [ -n "${GPU_MEM_512}" ]; then
		sed -i '/#gpu_mem_512/ c\gpu_mem_512=${GPU_MEM_512}' ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/config.txt
	fi

	# Video camera support
	if [ -n "${VIDEO_CAMERA}" ]; then
		echo "# Enable video camera" >>${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/config.txt
		echo "start_x=1" >>${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/config.txt
	fi
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
