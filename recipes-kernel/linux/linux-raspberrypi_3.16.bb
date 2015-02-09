LINUX_VERSION ?= "3.16.5"

SRCREV = "377c82aa1d31b37f1096096b0e4c65beb0bc5c49"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-3.16.y \
           file://sl030raspberrypii2ckernel.patch \
          "

require linux-raspberrypi.inc
