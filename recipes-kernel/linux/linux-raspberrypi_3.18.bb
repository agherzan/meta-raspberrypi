LINUX_VERSION ?= "3.18.5"

SRCREV = "a6cf3c99bc89e2c010c2f78fbf9e3ed478ccfd46"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-3.18.y \
           file://sl030raspberrypii2ckernel.patch \
          "

require linux-raspberrypi.inc
