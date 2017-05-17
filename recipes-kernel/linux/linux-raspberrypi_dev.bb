FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-dev:"

LINUX_VERSION ?= "4.11"
LINUX_RPI_DEV_BRANCH ?= "rpi-4.11.y"

SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=${LINUX_RPI_DEV_BRANCH} \
           file://0001-build-arm64-Add-rules-for-.dtbo-files-for-dts-overla.patch \
"
require linux-raspberrypi.inc

# A LOADADDR is needed when building a uImage format kernel. This value is not
# set by default in rpi-4.8.y and later branches so we need to provide it
# manually. This value unused if KERNEL_IMAGETYPE is not uImage.
KERNEL_EXTRA_ARGS += "LOADADDR=0x00008000"

# Disable version check so that we don't have to edit this recipe every time
# upstream bumps the version
KERNEL_VERSION_SANITY_SKIP = "1"
