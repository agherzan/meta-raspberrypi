DESCRIPTION = "RaspberryPi Test Packagegroup"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

inherit packagegroup

RDEPENDS_${PN} = "\
    omxplayer \
    bcm2835-tests \
    wiringpi \
    rpio \
    rpi-gpio \
    pi-blaster \
"

RRECOMMENDS_${PN} = "\
    bigbuckbunny-1080p \
    bigbuckbunny-480p \
    bigbuckbunny-720p \
"
