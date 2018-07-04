LINUX_VERSION ?= "4.14.52"

SRCREV = "36d224f4ae8759252a3583f147ae4487a9790073"
SRC_URI = " \
    git://github.com/raspberrypi/linux.git;branch=rpi-4.14.y \
    file://0001-menuconfig-check-lxdiaglog.sh-Allow-specification-of.patch \
    "

require linux-raspberrypi.inc
