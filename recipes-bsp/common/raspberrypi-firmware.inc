RPIFW_DATE ?= "20200306"
SRCREV ?= "163d84cbeb7038a4fd71d817eae43a535eb3df62"
RPIFW_SRC_URI ?= "https://github.com/raspberrypi/firmware/archive/${SRCREV}.tar.gz"
RPIFW_S ?= "${WORKDIR}/firmware-${SRCREV}"

SRC_URI = "${RPIFW_SRC_URI}"
SRC_URI[md5sum] = "8be0bd27fc4f31ecb0a9b48d8f27f933"
SRC_URI[sha256sum] = "4a62e6d37598300a42fc9c9f6b554e08f3ea825695e429f7f3674d4afc0d5803"

PV = "${RPIFW_DATE}"
