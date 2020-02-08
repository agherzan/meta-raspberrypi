RPIFW_DATE ?= "20200205"
SRCREV ?= "149cd7f0487e08e148efe604f8d4d359541cecf4"
RPIFW_SRC_URI ?= "https://github.com/raspberrypi/firmware/archive/${SRCREV}.tar.gz"
RPIFW_S ?= "${WORKDIR}/firmware-${SRCREV}"

SRC_URI = "${RPIFW_SRC_URI}"
SRC_URI[md5sum] = "8432e1a7ae5996e9ec6b817a6e97f78d"
SRC_URI[sha256sum] = "44713d27f1c8a1e65dd2d4b4db0d2123d5744930c70a84cc663da6ec76797c7e"

PV = "${RPIFW_DATE}"
