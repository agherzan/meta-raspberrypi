RPIFW_DATE ?= "20200819"
SRCREV ?= "f293685f683c48b1872beeb38c2f7da1f46141a0"
RPIFW_SRC_URI ?= "https://github.com/raspberrypi/firmware/archive/${SRCREV}.tar.gz"
RPIFW_S ?= "${WORKDIR}/firmware-${SRCREV}"

SRC_URI = "${RPIFW_SRC_URI}"
SRC_URI[sha256sum] = "dd825e8ccbd524182f7e06c5301f2ed2f8a68200e80cf61b1095271dcfbe0e55"

PV = "${RPIFW_DATE}"
