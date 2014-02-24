# 21/02/2014 firmware; this can be overridden from distro config
RPIFW_SRCREV ?= "cf20d522e926f30e91fa890d42d010ca98913286"
RPIFW_DATE ?= "20140221"
RPIFW_SRC_URI ?= "git://github.com/raspberrypi/firmware.git;protocol=git;branch=master"
RPIFW_S ?= "${WORKDIR}/git"

SRC_URI = "${RPIFW_SRC_URI}"
SRCREV = "${RPIFW_SRCREV}"
PV = "${RPIFW_DATE}"
