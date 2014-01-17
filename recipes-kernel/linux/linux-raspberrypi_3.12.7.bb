SRCREV = "7b3d6227aa8285f7cbd5b8ec767a840b6ac42477"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-3.12.y \
           file://sl030raspberrypii2ckernel.patch \
          "

require linux-raspberrypi.inc
