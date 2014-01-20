SUMMARY = "RaspberryPi tool to produce kernel.img"
LICENSE = "Broadcom"
LIC_FILES_CHKSUM = "file://${WORKDIR}/License;md5=957f6640d5e2d2acfce73a36a56cb32f"
SECTION = "bootloader"

DEPENDS = "python"

SRCREV = "330c72c2412f75a32932c4d9b51c9c678bce4180"
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
