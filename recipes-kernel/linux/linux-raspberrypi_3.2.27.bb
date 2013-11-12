SRCREV = "ada8b4415ff44d535d63e4291a0eca733bc2ad0f"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-3.2.27 \
           file://sl030raspberrypii2ckernel.patch \
          "

require linux-raspberrypi.inc
