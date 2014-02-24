SRCREV = "8f768c5f2a3314e4eacce8d667c787f8dadfda74"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-3.11.y \
           file://sl030raspberrypii2ckernel.patch \
          "

require linux-raspberrypi.inc
