RPIFW_DATE ?= "20231027"
SRCREV ?= "9814645c19bd8621fdd382e13369b1efea816c1c"
RPIFW_SRC_URI ?= "https://github.com/raspberrypi/firmware/archive/${SRCREV}.tar.gz;downloadfilename=raspberrypi-firmware-${SRCREV}.tar.gz"
RPIFW_S ?= "${WORKDIR}/firmware-${SRCREV}"

SRC_URI = "${RPIFW_SRC_URI}"
SRC_URI[sha256sum] = "3d6c65c891cf4264e959847d7d24447301f7ed936e9b5726cdd6c19d2a596cf9"

PV = "${RPIFW_DATE}"
