SRCREV = "cbd6672e7e1b2dc5026f5dc7929a13a9a68f2a62"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-3.6.y \
           file://sl030raspberrypii2ckernel.patch \
          "

require linux-raspberrypi.inc
