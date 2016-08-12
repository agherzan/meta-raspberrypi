RPIFW_SRCREV ?= "1205048f037098b1532bb947d1f4b823bcdebdf7"
RPIFW_DATE ?= "20160726"
RPIFW_SRC_URI ?= "git://github.com/raspberrypi/firmware.git;protocol=git;branch=master"
RPIFW_S ?= "${WORKDIR}/git"

SRC_URI = "${RPIFW_SRC_URI}"
SRCREV = "${RPIFW_SRCREV}"
PV = "${RPIFW_DATE}"
