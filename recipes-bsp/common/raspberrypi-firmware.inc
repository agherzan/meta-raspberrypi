RPIFW_DATE ?= "20200220"
SRCREV ?= "02bff4e75712094ffb1ab2ec58a8115ca3e52290"
RPIFW_SRC_URI ?= "https://github.com/raspberrypi/firmware/archive/${SRCREV}.tar.gz"
RPIFW_S ?= "${WORKDIR}/firmware-${SRCREV}"

SRC_URI = "${RPIFW_SRC_URI}"
SRC_URI[md5sum] = "0ff79476623af1182f6ddc4baeb7d2ed"
SRC_URI[sha256sum] = "08188344f592aecfa611a268b89762f7e48d08b1242185cb2627bb28e59d5f1a"

PV = "${RPIFW_DATE}"
