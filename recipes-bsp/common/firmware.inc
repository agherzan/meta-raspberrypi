RPIFW_SRCREV ?= "89881b5488a5a60088677333dcbd97e1836c0e7e"
RPIFW_DATE ?= "20150630"
RPIFW_SRC_URI ?= "git://github.com/raspberrypi/firmware.git;protocol=git;branch=master"
RPIFW_S ?= "${WORKDIR}/git"

SRC_URI = "${RPIFW_SRC_URI}"
SRCREV = "${RPIFW_SRCREV}"
PV = "${RPIFW_DATE}"
