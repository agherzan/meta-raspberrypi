SRCREV = "b975d0aab6816dd1dc4ff938a0c95bc551831163"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-3.11.y \
           file://sl030raspberrypii2ckernel.patch \
          "

require linux-raspberrypi.inc
