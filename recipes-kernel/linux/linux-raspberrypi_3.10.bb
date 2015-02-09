LINUX_VERSION ?= "3.10.38"

SRCREV = "1b49b450222df26e4abf7abb6d9302f72b2ed386"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-3.10.y \
           file://sl030raspberrypii2ckernel.patch \
          "

require linux-raspberrypi.inc
