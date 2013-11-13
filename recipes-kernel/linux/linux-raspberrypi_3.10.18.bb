SRCREV = "ae937f99fee8a37f2ddd7270f6bcc0e497e8c903"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-3.10.y \
           file://sl030raspberrypii2ckernel.patch \
          "

require linux-raspberrypi.inc
