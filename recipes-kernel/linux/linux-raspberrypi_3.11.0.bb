SRCREV = "d7474694bdc9836af17f4b4d839509f9aad7ffa7"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-3.11.y \
           file://sl030raspberrypii2ckernel.patch \
          "

require linux-raspberrypi.inc
