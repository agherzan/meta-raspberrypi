SRCREV = "839f349b6ed2a63a2bff0b5f0d718a7b07ca2316"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-3.12.y \
           file://sl030raspberrypii2ckernel.patch \
          "

require linux-raspberrypi.inc
