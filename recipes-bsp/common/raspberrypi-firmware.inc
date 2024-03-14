RPIFW_DATE ?= "20240312"
SRCREV = "5689dd184872b809af91d6636a832b1484d90fef"
RPIFW_SRC_URI ?= "git://github.com/raspberrypi/firmware;protocol=https;branch=master"
RPIFW_S ?= "${WORKDIR}/git"

SRC_URI = "${RPIFW_SRC_URI}"

PV = "${RPIFW_DATE}"

# Firmware repo is huge due to its content nature being binary blobs
# use shallow clone to reduce amount of git metadata being pulled down
BB_GIT_SHALLOW = "1"
