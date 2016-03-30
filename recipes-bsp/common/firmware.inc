RPIFW_SRCREV ?= "046effa13ebc4cc7601df4f06f4834bd0eebb0f8"
RPIFW_DATE ?= "20160326"
RPIFW_SRC_URI ?= "git://github.com/raspberrypi/firmware.git;protocol=git;branch=master"
RPIFW_S ?= "${WORKDIR}/git"

SRC_URI = "${RPIFW_SRC_URI}"
SRCREV = "${RPIFW_SRCREV}"
PV = "${RPIFW_DATE}"
