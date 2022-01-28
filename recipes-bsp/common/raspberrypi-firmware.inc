RPIFW_DATE ?= "20220120"
SRCREV ?= "c6d56567ff6ef17fd85159770f22abcf2c5953ed"
RPIFW_SRC_URI ?= "https://github.com/raspberrypi/firmware/archive/${SRCREV}.tar.gz;downloadfilename=raspberrypi-firmware-${SRCREV}.tar.gz"
RPIFW_S ?= "${WORKDIR}/firmware-${SRCREV}"

SRC_URI = "${RPIFW_SRC_URI}"
SRC_URI[sha256sum] = "9324356498f1b6a21f2af1229604c4de7c3b3ee8cc3e2b08c290366a0534fa75"

PV = "${RPIFW_DATE}"
