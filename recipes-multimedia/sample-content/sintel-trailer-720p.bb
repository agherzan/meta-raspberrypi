SUMMARY = "Sintel movie - 720P"
LICENSE = "CC-BY-3.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/CC-BY-3.0;md5=dfa02b5755629022e267f10b9c0a2ab7"

SRC_URI = "https://download.blender.org/durian/trailer/sintel_trailer-720p.mp4"
SRC_URI[md5sum] = "f62221dc4447d60073335a68cf4ac69f"
SRC_URI[sha256sum] = "cb0fe73fc0a7d543459996c0cdab730997e6eac1013d3ede18796f777cb7f273"

inherit allarch

do_install() {
    install -d ${D}${datadir}/movies
    install -m 0644 ${WORKDIR}/sintel_trailer-720p.mp4 ${D}${datadir}/movies/
}

FILES_${PN} += "${datadir}/movies"
