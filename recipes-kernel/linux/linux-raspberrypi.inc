DESCRIPTION = "Linux Kernel for Raspberry Pi"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

COMPATIBLE_MACHINE = "^rpi$"

PE = "1"
PV = "${LINUX_VERSION}+git${SRCPV}"

inherit siteinfo
require recipes-kernel/linux/linux-yocto.inc
require linux-raspberrypi-common.inc
