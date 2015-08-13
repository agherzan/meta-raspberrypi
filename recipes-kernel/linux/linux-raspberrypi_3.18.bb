LINUX_VERSION ?= "3.18.11"

SRCREV = "d64fa8121fca9883d6fb14ca06d2abf66496195e"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-3.18.y"

require linux-raspberrypi.inc
