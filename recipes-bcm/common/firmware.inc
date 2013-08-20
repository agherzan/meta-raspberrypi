# 19/08/2013 firmware; this can be overridden from distro config
RPIFW_SRCREV ?= "5b37b2a7f357236210eef1a438becaa2a57148f2"
RPIFW_DATE ?= "20130819"
RPIFW_SRC_URI ?= "git://github.com/raspberrypi/firmware.git;protocol=git;branch=master"
RPIFW_S ?= "${WORKDIR}/git"

SRC_URI = "${RPIFW_SRC_URI}"
SRCREV = "${RPIFW_SRCREV}"
PV = "${RPIFW_DATE}"
