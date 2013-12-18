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

SRC_URI[md5sum] = "35df4a6188e36796a07985f1561fb8a2"
SRC_URI[sha256sum] = "ea99538ea17ebed38973743d84b1358e8c0fd311e8828c4ef91694f65a1073ff"

PACKAGES += "${PN}-tests"

FILES_${PN} = ""
FILES_${PN}-tests = "${libdir}/${BPN}"
FILES_${PN}-dbg += "${libdir}/${BPN}/.debug"

inherit autotools

do_compile_append() {
    #Now compiling the examples provided by the package
    for file in examples/*
    do
        ${CC} ${file}/${file##*/}.c -o ${file}/${file##*/} -Bstatic -L${S}/src -lbcm2835 -I${S}/src
    done
}

do_install_append() {
    install -d ${D}/${libdir}/${BPN}
    for file in examples/*
    do
        install -m 0755 ${file}/${file##*/} ${D}/${libdir}/${BPN}
    done
}
