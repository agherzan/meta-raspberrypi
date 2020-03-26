LINUX_VERSION ?= "4.19.113"
LINUX_RPI_BRANCH ?= "rpi-4.19.y"

SRCREV = "4f2a4cc501c428c940549f39d5562e60404ac4f7"

require linux-raspberrypi_4.19.inc

SRC_URI += "file://0001-perf-Make-perf-able-to-build-with-latest-libbfd.patch \
            file://0001-selftest-bpf-Use-CHECK-macro-instead-of-RET_IF.patch \
           "
