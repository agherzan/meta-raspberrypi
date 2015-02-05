RPIFW_SRCREV ?= "d426cd1e79f0785fae6c37db79da7d6dae39716d"
RPIFW_DATE ?= "20150204"
RPIFW_SRC_URI ?= "git://github.com/raspberrypi/firmware.git;protocol=git;branch=master"
RPIFW_S ?= "${WORKDIR}/git"

SRC_URI = "${RPIFW_SRC_URI}"
SRCREV = "${RPIFW_SRCREV}"
PV = "${RPIFW_DATE}"
