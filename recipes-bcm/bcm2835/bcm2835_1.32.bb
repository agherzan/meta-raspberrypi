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

SRC_URI[md5sum] = "085239569554de5ee6649fe8bb123379"
SRC_URI[sha256sum] = "97e8aece4ff45b1779212473aa3b1d3f43cc9a8427afa3be718f690949e0ec20"


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
