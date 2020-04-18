RPIFW_DATE ?= "20200417"
SRCREV ?= "84523e0b9a9e78aa69fca1f1a8d75b2bdb5155fc"
RPIFW_SRC_URI ?= "https://github.com/raspberrypi/firmware/archive/${SRCREV}.tar.gz"
RPIFW_S ?= "${WORKDIR}/firmware-${SRCREV}"

SRC_URI = "${RPIFW_SRC_URI}"
SRC_URI[md5sum] = "657e10e0e9f12ad03a91ff0cd28ac628"
SRC_URI[sha256sum] = "93986d256d2791c3d151e01d12176f470ec085c93a32e0c85d7cec51bd4319c1"

PV = "${RPIFW_DATE}"
