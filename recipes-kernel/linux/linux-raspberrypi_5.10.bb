LINUX_VERSION ?= "5.10.13"
LINUX_RPI_BRANCH ?= "rpi-5.10.y"

SRCREV_machine = "34263dc81a12862c66e2593bb26c09d5fd20f46d"
SRCREV_meta = "5833ca701711d487c9094bd1efc671e8ef7d001e"

KMETA = "kernel-meta"

SRC_URI = " \
    git://github.com/raspberrypi/linux.git;name=machine;branch=${LINUX_RPI_BRANCH} \
    git://git.yoctoproject.org/yocto-kernel-cache;type=kmeta;name=meta;branch=yocto-5.10;destsuffix=${KMETA} \
    file://powersave.cfg \
    file://android-drivers.cfg \
    "

require linux-raspberrypi.inc

KERNEL_DTC_FLAGS += "-@ -H epapr"
