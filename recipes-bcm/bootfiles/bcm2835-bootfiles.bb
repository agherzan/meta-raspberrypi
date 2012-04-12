DESCRIPTION = "Closed source binary files to help boot the ARM on the BCM2835."
LICENSE = "proprietary-binary"

LIC_FILES_CHKSUM = "file://LICENCE;md5=d2defdc6f09addccc73d83b74f2dda58"

# This is on the master branch
SRCREV = "78d00079176a2751be9dd226f8463cbcc9c55073"         

SRC_URI = " \
        file://LICENCE \
        git://github.com/raspberrypi/firmware.git;protocol=git;branch=master  \    
"

S = "${WORKDIR}"
BOOT_FILES = "${S}/git/boot"

PR = "r0"

addtask deploy before do_package after do_install

do_deploy() {
	install -d ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles
	for i in ${BOOT_FILES}/*.elf ; do
		cp $i ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles
	done
	for i in ${BOOT_FILES}/*.bin ; do
		cp $i ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles
	done	
}

PACKAGE_ARCH = "${MACHINE_ARCH}"
