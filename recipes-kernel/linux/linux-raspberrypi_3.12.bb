LINUX_VERSION ?= "3.12.36"

SRCREV = "90fa5df724d147564149c7b79cb1ffc571a345ec"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-3.12.y \
           file://sl030raspberrypii2ckernel.patch \
          "

require linux-raspberrypi.inc
