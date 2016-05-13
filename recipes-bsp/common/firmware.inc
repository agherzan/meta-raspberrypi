RPIFW_SRCREV ?= "3816e1ce1e6ebc6d2bf0596dbd52849e16aa7e94"
RPIFW_DATE ?= "20160512"
RPIFW_SRC_URI ?= "git://github.com/raspberrypi/firmware.git;protocol=git;branch=master"
RPIFW_S ?= "${WORKDIR}/git"

SRC_URI = "${RPIFW_SRC_URI}"
SRCREV = "${RPIFW_SRCREV}"
PV = "${RPIFW_DATE}"
