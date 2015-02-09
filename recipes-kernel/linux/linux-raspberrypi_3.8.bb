LINUX_VERSION ?= "3.8.13"

SRCREV = "d996a1b91b2bf3dc06f4f4f822a56f4496457aa1"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-3.8.y \
           file://sl030raspberrypii2ckernel.patch \
          "

require linux-raspberrypi.inc
