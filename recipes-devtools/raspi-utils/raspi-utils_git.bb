SUMMARY = "A collection of scripts and simple applications"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENCE;md5=4c01239e5c3a3d133858dedacdbca63c"

DEPENDS:append = " dtc"

PV = "1.0+git"

SRC_URI = "git://github.com/raspberrypi/utils;protocol=https;branch=master"

SRCREV = "b9c63214c535d7df2b0fa6743b7b3e508363c25a"

S = "${WORKDIR}/git"

FILES:${PN}:append = " \
    ${datadir}/bash-completion/completions/pinctrl \
"

OECMAKE_TARGET_COMPILE = "pinctrl/all dtmerge/all"
OECMAKE_TARGET_INSTALL = "pinctrl/install dtmerge/install"

inherit cmake
