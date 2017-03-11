FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.9:"

LINUX_VERSION ?= "4.10.0-rc8"

SRCREV = "9aa91f43583fdb07306f962e6ee6797fba1cc62d"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.10.y \
"
require linux-raspberrypi.inc

# A LOADADDR is needed when building a uImage format kernel. This value is not
# set by default in rpi-4.8.y and later branches so we need to provide it
# manually. This value unused if KERNEL_IMAGETYPE is not uImage.
KERNEL_EXTRA_ARGS += "LOADADDR=0x00008000"
