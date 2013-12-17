# 19/08/2013 firmware; this can be overridden from distro config
RPIFW_SRCREV ?= "e618d4ff2dc20051bc726834b22487fe2e236fe5"
RPIFW_DATE ?= "20131217"
RPIFW_SRC_URI ?= "git://github.com/raspberrypi/firmware.git;protocol=git;branch=master"
RPIFW_S ?= "${WORKDIR}/git"

SRC_URI = "${RPIFW_SRC_URI}"
SRCREV = "${RPIFW_SRCREV}"
PV = "${RPIFW_DATE}"
