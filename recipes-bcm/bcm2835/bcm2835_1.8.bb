DESCRIPTION = "Package that provides access to GPIO and other IO\
functions on the Broadcom BCM 2835 chip, allowing access to the\
GPIO pins on the 26 pin IDE plug on the RPi board"
SECTION = "base"
HOMEPAGE = "http://www.open.com.au/mikem/bcm2835"
AUTHOR = "Mike McCauley (mikem@open.com.au)"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PR = "r0"

COMPATIBLE_MACHINE = "raspberrypi"

SRC_URI = "http://www.open.com.au/mikem/bcm2835/bcm2835-1.8.tar.gz"

SRC_URI[md5sum] = "cca8500049d4ebf9087de4bd1601d185"
SRC_URI[sha256sum] = "64be77b10aaf48ecb2a9022e13057f3b564093916875c0fc56373b4142dd5cae"

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
