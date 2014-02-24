SRCREV = "660b0008f5d318d8a29187b64c8717e75ad14c1c"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-3.13.y \
           file://sl030raspberrypii2ckernel.patch \
          "

require linux-raspberrypi.inc
