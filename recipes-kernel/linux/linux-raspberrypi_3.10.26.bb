SRCREV = "8c92f4407b5fa8b2a1c843e6c8c0ccb5c5a0b762"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-3.10.y \
           file://sl030raspberrypii2ckernel.patch \
          "

require linux-raspberrypi.inc
