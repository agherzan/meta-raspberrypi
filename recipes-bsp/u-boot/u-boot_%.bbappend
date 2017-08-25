FILESEXTRAPATHS_prepend := "${THISDIR}/u-boot:"

SRC_URI_append_rpi = " \
    file://0001-Revert-dm-arm-rpi-Drop-CONFIG_OF_EMBED.patch \
    file://0002-rpi-Enable-USB-keyboard-support.patch \
    "

RDEPENDS_${PN}_append_rpi = " rpi-u-boot-scr"
