RPIFW_SRCREV ?= "c5e131971c323f5658c7c5d4b1dd105d37903a99"
RPIFW_DATE ?= "20160421"
RPIFW_SRC_URI ?= "git://github.com/raspberrypi/firmware.git;protocol=git;branch=master"
RPIFW_S ?= "${WORKDIR}/git"

SRC_URI = "${RPIFW_SRC_URI}"
SRCREV = "${RPIFW_SRCREV}"
PV = "${RPIFW_DATE}"
