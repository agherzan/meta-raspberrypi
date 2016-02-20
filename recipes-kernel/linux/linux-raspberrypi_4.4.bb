LINUX_VERSION ?= "4.4.2"

SRCREV = "8941fe4985a1cc8f800be00224c6a2e741789d03"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.4.y"

require linux-raspberrypi.inc
