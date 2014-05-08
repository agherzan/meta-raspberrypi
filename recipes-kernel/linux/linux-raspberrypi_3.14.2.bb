SRCREV = "b0da29026e2bd5602fed7f650124838696f9dda6"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-3.14.y \
           file://sl030raspberrypii2ckernel.patch \
          "

require linux-raspberrypi.inc
