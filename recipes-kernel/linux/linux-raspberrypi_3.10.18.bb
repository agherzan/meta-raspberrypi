SRCREV = "b49aafd02fa7572d387acd34550beea5b4c3d239"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-3.10.y \
           file://sl030raspberrypii2ckernel.patch \
          "

require linux-raspberrypi.inc
