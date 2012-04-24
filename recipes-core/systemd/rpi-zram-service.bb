DESCRIPTION = "Linux zram compressed in-memory swap systemd service"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

inherit allarch systemd

PR = "r0"

SRC_URI = "file://rpi-zram.service \
           file://rpi-load-zram.sh \
          "

do_compile() {
    :
}


do_install () {
	install -d ${D}/${bindir}

	install -m 0755 ${WORKDIR}/rpi-load-zram.sh ${D}/${bindir}
	
	install -d ${D}/${base_libdir}/systemd/system
	install -m 0644 ${WORKDIR}/rpi-zram.service ${D}/${base_libdir}/systemd/system/
}

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "rpi-zram.service"

FILES_${PN} += "${base_libdir}/systemd"
