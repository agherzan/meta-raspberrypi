LINUX_VERSION ?= "5.4.29"
LINUX_RPI_BRANCH ?= "rpi-5.4.y"

SRCREV = "5c08b6e79a8bfa1e59bb0004a1c94ed902a6d615"

require linux-raspberrypi_5.4.inc

SRC_URI += "file://0001-perf-Make-perf-able-to-build-with-latest-libbfd.patch \
            file://powersave.cfg \
           "
