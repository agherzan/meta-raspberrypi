SRCREV = "ff9f6d87957877265086ab8368bdabf27d35f491"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-3.10.y \
           file://sl030raspberrypii2ckernel.patch \
          "

require linux-raspberrypi.inc
