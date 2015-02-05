SUMMARY = "RaspberryPi tool to produce kernel.img"
LICENSE = "Broadcom"
LIC_FILES_CHKSUM = "file://${WORKDIR}/License;md5=957f6640d5e2d2acfce73a36a56cb32f"
SECTION = "bootloader"

DEPENDS = "python"

SRCREV = "b9d7a8ac626e377577aa6900da5c5a965cafb66c"
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
