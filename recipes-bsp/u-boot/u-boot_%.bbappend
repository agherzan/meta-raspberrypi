FILESEXTRAPATHS_prepend := "${THISDIR}/u-boot:"

SRC_URI_append_rpi = " \
    file://0001-add-support-for-Raspberry-Pi-Zero-W.patch \
    file://0002-rpi_0_w-Add-configs-consistent-with-RpI3.patch \
"

RDEPENDS_${PN}_append_rpi = " rpi-u-boot-scr"
