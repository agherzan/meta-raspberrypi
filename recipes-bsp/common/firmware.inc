RPIFW_DATE ?= "20180209"
RPIFW_SRC_URI ?= "https://github.com/raspberrypi/firmware/archive/b1a7f4aea6cbd380319c2849ecc5988f9a4dba70.tar.gz"
RPIFW_S ?= "${WORKDIR}/firmware-b1a7f4aea6cbd380319c2849ecc5988f9a4dba70"

SRC_URI = "${RPIFW_SRC_URI}"
SRC_URI[md5sum] = "08ed10d92ee064711d73790557267f84"
SRC_URI[sha256sum] = "0ad8b66c02ba14917dfdc15f11d702da650c473681d100efb3f8d47aee9b8ece"
PV = "${RPIFW_DATE}"

