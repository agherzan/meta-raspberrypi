FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

DEPENDS_append_rpi = " u-boot-default-script"

SRC_URI_append_raspberrypi-cm3 = " file://0001-dm-core-Move-ofdata_to_platdata-call-earlier.patch"
