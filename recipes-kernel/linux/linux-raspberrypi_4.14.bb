LINUX_VERSION ?= "4.14.34"

SRCREV = "f70eae405b5d75f7c41ea300b9f790656f99a203"
SRC_URI = " \
    git://github.com/raspberrypi/linux.git;branch=rpi-4.14.y \
    file://0001-menuconfig-check-lxdiaglog.sh-Allow-specification-of.patch \
    "

require linux-raspberrypi.inc
