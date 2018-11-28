LINUX_VERSION ?= "4.14.85"

SRCREV = "802d8776632344a4354d8ef5f142611a4c878570"
SRC_URI = " \
    git://github.com/raspberrypi/linux.git;branch=rpi-4.14.y \
    file://0001-menuconfig-check-lxdiaglog.sh-Allow-specification-of.patch \
    "

require linux-raspberrypi.inc
