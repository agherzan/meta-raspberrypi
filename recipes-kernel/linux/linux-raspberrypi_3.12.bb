LINUX_VERSION ?= "3.12.36"

SRCREV = "ee9b8c7d46f2b1787b1e64604acafc70f70191cf"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-3.12.y \
          "

require linux-raspberrypi.inc
