RPIFW_DATE ?= "20190620"
SRCREV ?= "88ca9081f5e51cdedd16d5dbc85ed12a25123201"
RPIFW_SRC_URI ?= "https://github.com/raspberrypi/firmware/archive/${SRCREV}.tar.gz"
RPIFW_S ?= "${WORKDIR}/firmware-${SRCREV}"

SRC_URI = "${RPIFW_SRC_URI}"
SRC_URI[md5sum] = "cae293a2af57ba5a347eb8b95710805e"
SRC_URI[sha256sum] = "17e30e75e5d5108c8c45f550d34c8c934961703ddb64f753bf97b90791b3aa0c"

PV = "${RPIFW_DATE}"
