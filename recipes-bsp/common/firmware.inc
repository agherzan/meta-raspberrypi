RPIFW_SRCREV ?= "028b1f6585280bd0a21885973589c4b101355597"
RPIFW_DATE ?= "20160303"
RPIFW_SRC_URI ?= "git://github.com/raspberrypi/firmware.git;protocol=git;branch=master"
RPIFW_S ?= "${WORKDIR}/git"

SRC_URI = "${RPIFW_SRC_URI}"
SRCREV = "${RPIFW_SRCREV}"
PV = "${RPIFW_DATE}"
