SUMMARY = "Sintel movie - 480P"
LICENSE = "CC-BY-3.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/CC-BY-3.0;md5=dfa02b5755629022e267f10b9c0a2ab7"

SRC_URI = "https://download.blender.org/durian/trailer/sintel_trailer-480p.mp4"
SRC_URI[md5sum] = "df6ed4bbc93613c68c8525e21bbddf98"
SRC_URI[sha256sum] = "b670602fa00934ca27c4351bb0efe7ea7a07fae57284e44226025eeed7c51254"

inherit allarch

do_install() {
    install -d ${D}${datadir}/movies
    install -m 0644 ${WORKDIR}/sintel_trailer-480p.mp4 ${D}${datadir}/movies/
}

FILES_${PN} += "${datadir}/movies"
