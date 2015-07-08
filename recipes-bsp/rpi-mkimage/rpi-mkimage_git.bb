SUMMARY = "RaspberryPi tool to produce kernel.img"
LICENSE = "Broadcom"
LIC_FILES_CHKSUM = "file://${WORKDIR}/License;md5=957f6640d5e2d2acfce73a36a56cb32f"
SECTION = "bootloader"

DEPENDS = "python"

SRCREV = "d4b397f3a1e60ea45660eafb9209bcb97453c456"
SRC_URI = " \
    git://github.com/raspberrypi/tools.git;branch=master;protocol=git \
    file://License \
    file://open-files-relative-to-script.patch \
"

S = "${WORKDIR}/git"

do_install () {
    install -d ${D}${libexecdir}
    cp ./mkimage/* ${D}${libexecdir}
}

BBCLASSEXTEND = "native"
