LINUX_VERSION ?= "4.14.79"

SRCREV = "9ca74c53cbda1f104bce3b33850fd3bf33eb3793"
SRC_URI = " \
    git://github.com/raspberrypi/linux.git;branch=rpi-4.14.y \
    file://0001-menuconfig-check-lxdiaglog.sh-Allow-specification-of.patch \
    file://0001-Bluetooth-hci_ldisc-Free-rw_semaphore-on-close.patch \
    "

require linux-raspberrypi.inc
