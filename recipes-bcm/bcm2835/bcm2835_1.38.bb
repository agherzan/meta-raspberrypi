DESCRIPTION = "Package that provides access to GPIO and other IO\
functions on the Broadcom BCM 2835 chip, allowing access to the\
GPIO pins on the 26 pin IDE plug on the RPi board"
SECTION = "base"
HOMEPAGE = "http://www.open.com.au/mikem/bcm2835"
AUTHOR = "Mike McCauley (mikem@open.com.au)"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

COMPATIBLE_MACHINE = "raspberrypi"

SRC_URI = "http://www.open.com.au/mikem/bcm2835/bcm2835-${PV}.tar.gz"

SRC_URI[md5sum] = "22d431f7402b9c7f93baef348a459cb1"
SRC_URI[sha256sum] = "90c993559ea273ae2e0587f6b815f7c80f19f47ee7f8aa7799b883f975196dbe"

inherit autotools

do_compile_append() {
    # Now compiling the examples provided by the package
    mkdir -p ${B}/examples
    for file in `ls ${S}/examples`; do
        ${CC} ${S}/examples/${file}/${file}.c -o ${B}/examples/${file} -Bstatic -L${B}/src -lbcm2835 -I${S}/src
    done
}

do_install_append() {
    install -d ${D}/${libdir}/${BPN}
    for file in ${B}/examples/*
    do
        install -m 0755 ${file} ${D}/${libdir}/${BPN}
    done
}

PACKAGES += "${PN}-tests"

FILES_${PN} = ""
FILES_${PN}-tests = "${libdir}/${BPN}"
FILES_${PN}-dbg += "${libdir}/${BPN}/.debug"
