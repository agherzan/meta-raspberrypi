DESCRIPTION = "Tools to append 32KB binary header to kernel image."
LICENSE = "proprietary-binary"

COMPATIBLE_MACHINE = "raspberrypi"
PR = "${MACHINE_KERNEL_PR}.3"

SECTION = "bootloader"

LIC_FILES_CHKSUM = "file://LICENCE;md5=3d7292881293368c0a9f3bc521c2b87e"

SRC_URI = " \
        file://LICENCE \
        file://bcm2835-args-uncompressed.txt \
        file://bcm2835-boot-uncompressed.txt \
        file://bcm2835-mkimage.py \
        file://bcm2835-kernel-first32k.bin \        
"

S = "${WORKDIR}"

BBCLASSEXTEND = "native nativesdk"

do_install () {
  install -d ${D}${bindir}
  install -m 0755 bcm2835-mkimage.py ${D}${bindir}
  install -m 0644 bcm2835-args-uncompressed.txt ${D}${bindir}
  install -m 0644 bcm2835-boot-uncompressed.txt ${D}${bindir}
  install -m 0644 bcm2835-kernel-first32k.bin ${D}${bindir}
}

FILES_${PN} = " \
        ${bindir}/bcm2835-* \
"
