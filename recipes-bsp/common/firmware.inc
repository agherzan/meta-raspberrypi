RPIFW_SRCREV ?= "413fc664838e81caeabdc4118dc51e138d5d6c71"
RPIFW_DATE ?= "20161030"
RPIFW_SRC_URI ?= "git://github.com/raspberrypi/firmware.git;protocol=git;branch=master"
RPIFW_S ?= "${WORKDIR}/git"

SRC_URI = "${RPIFW_SRC_URI}"
SRCREV = "${RPIFW_SRCREV}"
PV = "${RPIFW_DATE}"
