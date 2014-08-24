RPIFW_SRCREV ?= "43c5b2fc9bdb0a43ba67661b8677445e71ae9e82"
RPIFW_DATE ?= "20140817"
RPIFW_SRC_URI ?= "git://github.com/raspberrypi/firmware.git;protocol=git;branch=master"
RPIFW_S ?= "${WORKDIR}/git"

SRC_URI = "${RPIFW_SRC_URI}"
SRCREV = "${RPIFW_SRCREV}"
PV = "${RPIFW_DATE}"
