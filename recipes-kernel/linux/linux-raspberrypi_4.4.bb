LINUX_VERSION ?= "4.4.3"

SRCREV = "36babd89241c85258acebe06616f1f1a58356f8e"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.4.y"

require linux-raspberrypi.inc
