SUMMARY = "Sintel movie - 1080P"
LICENSE = "CC-BY-3.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/CC-BY-3.0;md5=dfa02b5755629022e267f10b9c0a2ab7"

SRC_URI = "https://download.blender.org/durian/trailer/sintel_trailer-1080p.mp4"
SRC_URI[md5sum] = "bfd19c4e7ad04c72bfb327cf7e6b9576"
SRC_URI[sha256sum] = "34bbd52a4b89fdf63c8ace50b268da26653a59508288100cd3c23de276db7931"

inherit allarch

do_install() {
    install -d ${D}${datadir}/movies
    install -m 0644 ${WORKDIR}/sintel_trailer-1080p.mp4 ${D}${datadir}/movies/
}

FILES_${PN} += "${datadir}/movies"
