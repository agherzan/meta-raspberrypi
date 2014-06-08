SRCREV = "cb53ea88f75180cc1ba74f7f197c8e3fd4f47cfe"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-3.12.y \
           file://sl030raspberrypii2ckernel.patch \
          "

require linux-raspberrypi.inc
