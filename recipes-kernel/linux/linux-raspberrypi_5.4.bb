LINUX_VERSION ?= "5.4.79"
LINUX_RPI_BRANCH ?= "rpi-5.4.y"

SRCREV_machine = "9797f1a4938c20139b00a25de93cc99efb5c291b"
SRCREV_meta = "5d52d9eea95fa09d404053360c2351b2b91b323b"

require linux-raspberrypi_5.4.inc

SRC_URI += "file://0001-Revert-selftests-bpf-Skip-perf-hw-events-test-if-the.patch \
            file://0002-Revert-selftests-bpf-Fix-perf_buffer-test-on-systems.patch \
            file://powersave.cfg \
            file://android-drivers.cfg \
           "
