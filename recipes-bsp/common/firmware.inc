RPIFW_DATE ?= "20161215"
RPIFW_SRC_URI ?= "https://github.com/raspberrypi/firmware/archive/1.${RPIFW_DATE}.tar.gz"
RPIFW_S ?= "${WORKDIR}/firmware-1.${RPIFW_DATE}"

SRC_URI = "${RPIFW_SRC_URI}"
SRC_URI[md5sum] = "ddd7645988360d7ef267b48c32293ad7"
SRC_URI[sha256sum] = "bda18f2affb50053940fd88c3f3bec5af9a4b4ced753d01107a2b106cfb02d13"

PV = "${RPIFW_DATE}"

