SRCREV = "71397e18fb1038619f3066827b83d6fcd5d8bd62"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-3.11.y \
           file://sl030raspberrypii2ckernel.patch \
          "

require linux-raspberrypi.inc
