LINUX_VERSION ?= "4.19.115"
LINUX_RPI_BRANCH ?= "rpi-4.19.y"

SRCREV = "b13fc60b529fe9e4fee4a7a5caf73d582003abfa"

require linux-raspberrypi_4.19.inc

SRC_URI += "file://0001-perf-Make-perf-able-to-build-with-latest-libbfd.patch \
            file://0001-selftest-bpf-Use-CHECK-macro-instead-of-RET_IF.patch \
           "
