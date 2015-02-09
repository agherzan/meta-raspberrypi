LINUX_VERSION ?= "3.6.11"

SRCREV = "2a8d45ec0883e3cbdce920855b3461ac77308a5f"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-3.6.y \
           file://sl030raspberrypii2ckernel.patch \
          "

require linux-raspberrypi.inc
