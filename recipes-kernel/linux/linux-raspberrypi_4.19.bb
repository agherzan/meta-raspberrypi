LINUX_VERSION ?= "4.19.108"
LINUX_RPI_BRANCH ?= "rpi-4.19.y"

SRCREV = "4137a0c092ab26fd3b40f49b321988e99800d085"

require linux-raspberrypi_4.19.inc

SRC_URI += "file://0001-perf-Make-perf-able-to-build-with-latest-libbfd.patch \
            file://0001-selftest-bpf-Use-CHECK-macro-instead-of-RET_IF.patch \
           "
