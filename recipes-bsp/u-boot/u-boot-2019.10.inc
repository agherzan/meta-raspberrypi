FILESEXTRAPATHS_prepend := "${THISDIR}/u-boot-2019.10:"

SRC_URI_append_rpi = " \
    file://0001-libfdt-fdt_address_cells-and-fdt_size_cells.patch \
    file://0002-libfdt-return-correct-value-if-size-cells-property-i.patch \
    file://0003-libfdt-Allow-size-cells-of-0.patch \
    file://0004-dm-Fix-default-address-cells-return-value.patch \
"
