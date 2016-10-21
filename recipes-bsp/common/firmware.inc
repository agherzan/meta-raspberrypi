RPIFW_SRCREV ?= "ec5a517ed6051974a5a09f7b7811d21c443a7c74"
RPIFW_DATE ?= "20161020"
RPIFW_SRC_URI ?= "git://github.com/raspberrypi/firmware.git;protocol=git;branch=master"
RPIFW_S ?= "${WORKDIR}/git"

SRC_URI = "${RPIFW_SRC_URI}"
SRCREV = "${RPIFW_SRCREV}"
PV = "${RPIFW_DATE}"
