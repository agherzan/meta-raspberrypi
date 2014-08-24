SRCREV = "82692a293288c334f3da11895e63ea7d066db4f6"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-3.14.y \
           file://sl030raspberrypii2ckernel.patch \
          "

require linux-raspberrypi.inc
