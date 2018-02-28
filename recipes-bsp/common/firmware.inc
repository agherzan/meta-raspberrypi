RPIFW_DATE ?= "20180226"
SRCREV ?= "469fc794fd3e45dbf30e14b46cb0d6283e399cba"
RPIFW_SRC_URI ?= "https://github.com/raspberrypi/firmware/archive/${SRCREV}.tar.gz"
RPIFW_S ?= "${WORKDIR}/firmware-${SRCREV}"

SRC_URI = "${RPIFW_SRC_URI}"
SRC_URI[md5sum] = "319e53a3184d5cec6ba78a29d001940c"
SRC_URI[sha256sum] = "002128368c15b1e030d9a625db49603d7d19973ba58eee11973a9319f6ba5d60"

PV = "${RPIFW_DATE}"

