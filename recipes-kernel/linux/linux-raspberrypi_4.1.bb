LINUX_VERSION ?= "4.1.9"

SRCREV = "21c2c0a01bad53c25c9627aef9f33de241076f8d"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.1.y"

require linux-raspberrypi.inc
