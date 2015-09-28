LINUX_VERSION ?= "4.1.8"

SRCREV = "d2b2388d05d8a97b0ba14fcf2b71f19f66bc4d02"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.1.y"

require linux-raspberrypi.inc

#KERNEL_DEVICETREE = " \
#    bcm2708-rpi-b.dtb \
#    bcm2708-rpi-b-plus.dtb \
#    bcm2709-rpi-2-b.dtb \ 
#    overlays/hifiberry-amp-overlay.dtb \
#    overlays/hifiberry-dac-overlay.dtb \
#    overlays/hifiberry-dacplus-overlay.dtb \
#    overlays/hifiberry-digi-overlay.dtb \
#    overlays/i2c-rtc-overlay.dtb \
#    overlays/iqaudio-dac-overlay.dtb \
#    overlays/iqaudio-dacplus-overlay.dtb \
#    overlays/lirc-rpi-overlay.dtb \
#    overlays/pps-gpio-overlay.dtb \
#    overlays/w1-gpio-overlay.dtb \
#    overlays/w1-gpio-pullup-overlay.dtb \
#    overlays/rpi-ft5406-overlay.dtb \
#    "
#KERNEL_DEVICETREE_remove = "ds1307-rtc-overlay.dtb"


#do_install_prepend() {
#    install -d ${D}/lib/firmware
#    if [ -n "${KERNEL_DEVICETREE}" ]; then
#        mkdir -p ${B}/arch/${ARCH}/boot/dts/overlays
#    fi
#}
