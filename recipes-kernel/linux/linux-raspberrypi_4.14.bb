LINUX_VERSION ?= "4.14.87"

SRCREV = "faf0452f03b09c7063a2b14b23f61b3bd7eb264a"
SRC_URI = " \
    git://github.com/raspberrypi/linux.git;branch=rpi-4.14.y \
    file://0001-menuconfig-check-lxdiaglog.sh-Allow-specification-of.patch \
    "

require linux-raspberrypi.inc
