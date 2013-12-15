DESCRIPTION = "A library to control Raspberry Pi GPIO channels"
HOMEPAGE = "https://projects.drogon.net/raspberry-pi/wiringpi/"
SECTION = "devel/libs"
LICENSE = "LGPLv3+"
LIC_FILES_CHKSUM = "file://COPYING.LESSER;md5=e6a600fd5e1d9cbde2d983680233ad02"

SRCREV = "98bcb20d9391ebde24f9eb1244f0d238fb1a1dab"

S = "${WORKDIR}/git"

SRC_URI = "\
          git://git.drogon.net/wiringPi \
          file://Makefiles-install.patch \
          "

COMPATIBLE_MACHINE = "raspberrypi"

CFLAGS_prepend = "-I${S}/wiringPi"

EXTRA_OEMAKE += "'INCLUDE_DIR=${D}${includedir}' 'LIB_DIR=${D}${libdir}'"
EXTRA_OEMAKE += "'DESTDIR=${D}/usr' 'PREFIX=""'"

do_compile() {
    oe_runmake -C wiringPi
    oe_runmake -C gpio 'LDFLAGS=${LDFLAGS} -L${S}/wiringPi'
}

do_install() {
    oe_runmake -C wiringPi install
    oe_runmake -C gpio install
}
