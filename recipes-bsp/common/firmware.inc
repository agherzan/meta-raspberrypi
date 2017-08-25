RPIFW_DATE ?= "20170811"
RPIFW_SRC_URI ?= "https://github.com/raspberrypi/firmware/archive/1.${RPIFW_DATE}.tar.gz"
RPIFW_S ?= "${WORKDIR}/firmware-1.${RPIFW_DATE}"

SRC_URI = "${RPIFW_SRC_URI}"
SRC_URI[md5sum] = "afd09f9a6df14e32b6d832fd9f51d087"
SRC_URI[sha256sum] = "a25f6281d64732892a2e838cc2346f1a88505b5c77a57a6540755362ea64043a"

PV = "${RPIFW_DATE}"

