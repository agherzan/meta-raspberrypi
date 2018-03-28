RPIFW_DATE ?= "20180313"
SRCREV ?= "af994023ab491420598bfd5648b9dcab956f7e11"
RPIFW_SRC_URI ?= "https://github.com/raspberrypi/firmware/archive/${SRCREV}.tar.gz"
RPIFW_S ?= "${WORKDIR}/firmware-${SRCREV}"

SRC_URI = "${RPIFW_SRC_URI}"
SRC_URI[md5sum] = "0c388f3631093368ac92a15a262d0f7c"
SRC_URI[sha256sum] = "e2afa23886de586856a1d604da2a85e4559715816dccbd70ae828b840beafc21"

PV = "${RPIFW_DATE}"
