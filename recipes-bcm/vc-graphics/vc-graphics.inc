DESCRIPTION = "Graphics libraries for BCM2835."
LICENSE = "Proprietary"

LIC_FILES_CHKSUM = "file://LICENCE;md5=86e53f5f5909ee66900418028de11780"

PROVIDES += "virtual/egl"

include ../common/firmware.inc

SRC_URI = "git://github.com/raspberrypi/firmware.git;protocol=git;branch=master\
           file://egl.pc"

S = "${WORKDIR}/git/${VCDIR}"

INCPR = "r0"

inherit pkgconfig

do_install () {
  install -d ${D}${bindir}
  cp -R bin/* ${D}${bindir}

  install -d ${D}${sbindir}
  cp -R sbin/* ${D}${sbindir}

  install -d ${D}${libdir}
  cp -R lib/* ${D}${libdir}

  install -d ${D}${includedir}
  cp -R include/* ${D}${includedir}

  install -d ${D}${libdir}/pkgconfig
  install -m 0644 ${WORKDIR}/egl.pc ${D}${libdir}/pkgconfig/
}

FILES_${PN} = "${bindir}/* \
               ${sbindir}/* \
               ${libdir}/lib*.so"
FILES_${PN}-dev = "${libdir}/pkgconfig \
                   ${includedir}"
