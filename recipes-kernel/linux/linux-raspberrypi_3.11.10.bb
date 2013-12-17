SRCREV = "639f74acc37561caa67abeb9fb61adc7e6a49aef"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-3.11.y \
           file://sl030raspberrypii2ckernel.patch \
          "

require linux-raspberrypi.inc
