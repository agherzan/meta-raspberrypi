RPIFW_SRCREV ?= "f7108cfdc715f79c5fd051fb4c3ae6f4b1b01f23"
RPIFW_DATE ?= "20151021"
RPIFW_SRC_URI ?= "git://github.com/raspberrypi/firmware.git;protocol=git;branch=master"
RPIFW_S ?= "${WORKDIR}/git"

SRC_URI = "${RPIFW_SRC_URI}"
SRCREV = "${RPIFW_SRCREV}"
PV = "${RPIFW_DATE}"
