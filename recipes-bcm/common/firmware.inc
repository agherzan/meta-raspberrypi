# 19/08/2013 firmware; this can be overridden from distro config
RPIFW_SRCREV ?= "3e92add424c3c2617d5df65caa307c808017a9ea"
RPIFW_DATE ?= "20131113"
RPIFW_SRC_URI ?= "git://github.com/raspberrypi/firmware.git;protocol=git;branch=master"
RPIFW_S ?= "${WORKDIR}/git"

SRC_URI = "${RPIFW_SRC_URI}"
SRCREV = "${RPIFW_SRCREV}"
PV = "${RPIFW_DATE}"
