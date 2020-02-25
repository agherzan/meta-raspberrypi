LINUX_VERSION ?= "4.19.105"
LINUX_RPI_BRANCH ?= "rpi-4.19.y"

SRCREV = "e645cec69367ba4b6daf63933f48661ab4b59ee1"

require linux-raspberrypi_4.19.inc

SRC_URI += "file://0001-perf-Make-perf-able-to-build-with-latest-libbfd.patch"
