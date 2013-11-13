SRCREV = "10bc58289a7ebf6b1ab724636e0a16116278e339"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-3.6.y \
           file://sl030raspberrypii2ckernel.patch \
          "

require linux-raspberrypi.inc
