RPIFW_SRCREV ?= "ec63df146f454e8cab7080380f9138246d877013"
RPIFW_DATE ?= "20161005"
RPIFW_SRC_URI ?= "git://github.com/raspberrypi/firmware.git;protocol=git;branch=master"
RPIFW_S ?= "${WORKDIR}/git"

SRC_URI = "${RPIFW_SRC_URI}"
SRCREV = "${RPIFW_SRCREV}"
PV = "${RPIFW_DATE}"
