SUMMARY = "A collection of scripts and simple applications"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENCE;md5=4c01239e5c3a3d133858dedacdbca63c"

DEPENDS:append = " dtc"
RDEPENDS:${PN}:append = "bash"

PV = "1.0+git"

SRC_URI = "git://github.com/raspberrypi/utils;protocol=https;branch=master"

SRCREV = "b9c63214c535d7df2b0fa6743b7b3e508363c25a"

S = "${WORKDIR}/git"

FILES:${PN}:append = " \
    ${datadir}/bash-completion/completions/pinctrl \
    ${datadir}/bash-completion/completions/vcgencmd \
"

OECMAKE_TARGET_COMPILE = "\
    dtmerge/all \
    eeptools/all \
    kdtc/all \
    otpset/all \
    overlaycheck/all \
    ovmerge/all \
    pinctrl/all \
    piolib/examples \
    raspinfo/all \
    vcgencmd/all \
    vclog/all \
    vcmailbox/all \
"

OECMAKE_TARGET_INSTALL = "\
    dtmerge/install \
    eeptools/install \
    kdtc/install \
    otpset/install \
    overlaycheck/install \
    ovmerge/install \
    pinctrl/install \
    piolib/install \
    raspinfo/install \
    vcgencmd/install \
    vclog/install \
    vcmailbox/install \
"

inherit cmake
