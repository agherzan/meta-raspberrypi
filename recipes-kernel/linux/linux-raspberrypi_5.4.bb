LINUX_VERSION ?= "5.4.32"
LINUX_RPI_BRANCH ?= "rpi-5.4.y"

SRCREV = "df9a97fcc1ec5abb570d9c8ae5e24939380d6902"

require linux-raspberrypi_5.4.inc

SRC_URI += "file://0001-perf-Make-perf-able-to-build-with-latest-libbfd.patch \
            file://0001-Revert-selftests-bpf-Skip-perf-hw-events-test-if-the.patch \
            file://0002-Revert-selftests-bpf-Fix-perf_buffer-test-on-systems.patch \
            file://0001-selftest-bpf-Use-CHECK-macro-instead-of-RET_IF.patch \
            file://powersave.cfg \
           "
