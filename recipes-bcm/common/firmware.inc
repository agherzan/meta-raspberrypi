# 17/01/2014 firmware; this can be overridden from distro config
RPIFW_SRCREV ?= "9babf5f124b85293f81d78b031a15da0d51d46bd"
RPIFW_DATE ?= "20140117"
RPIFW_SRC_URI ?= "git://github.com/raspberrypi/firmware.git;protocol=git;branch=master"
RPIFW_S ?= "${WORKDIR}/git"

SRC_URI = "${RPIFW_SRC_URI}"
SRCREV = "${RPIFW_SRCREV}"
PV = "${RPIFW_DATE}"
