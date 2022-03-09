LINUX_VERSION ?= "5.15.27"
LINUX_RPI_BRANCH ?= "rpi-5.15.y"
LINUX_RPI_KMETA_BRANCH ?= "yocto-5.15"

SRCREV_machine = "cad0c67b64bb9de6bc970c0a7afbccabec0fcf9d"
SRCREV_meta = "e1b976ee4fb5af517cf01a9f2dd4a32f560ca894"

KMETA = "kernel-meta"

SRC_URI = " \
    git://github.com/raspberrypi/linux.git;name=machine;branch=${LINUX_RPI_BRANCH};protocol=https \
    git://git.yoctoproject.org/yocto-kernel-cache;type=kmeta;name=meta;branch=${LINUX_RPI_KMETA_BRANCH};destsuffix=${KMETA} \
    file://powersave.cfg \
    file://android-drivers.cfg \
    "

require linux-raspberrypi.inc

KERNEL_DTC_FLAGS += "-@ -H epapr"
