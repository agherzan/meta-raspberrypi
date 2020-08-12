LINUX_VERSION ?= "5.4.51"
LINUX_RPI_BRANCH ?= "rpi-5.4.y"

SRCREV = "4b945d5fb69233f4ddf66d67c3d35145f1039e01"

require linux-raspberrypi_5.4.inc

SRC_URI += "file://0001-Revert-selftests-bpf-Skip-perf-hw-events-test-if-the.patch \
            file://0002-Revert-selftests-bpf-Fix-perf_buffer-test-on-systems.patch \
            file://powersave.cfg \
           "
