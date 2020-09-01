LINUX_VERSION ?= "5.4.59"
LINUX_RPI_BRANCH ?= "rpi-5.4.y"

SRCREV = "423495b33efd681dc1c8be10b1303e679216be4c"

require linux-raspberrypi_5.4.inc

SRC_URI += "file://0001-Revert-selftests-bpf-Skip-perf-hw-events-test-if-the.patch \
            file://0002-Revert-selftests-bpf-Fix-perf_buffer-test-on-systems.patch \
            file://0001-perf-cs-etm-Move-definition-of-traceid_list-global-v.patch \
            file://powersave.cfg \
            file://android-drivers.cfg \
           "
