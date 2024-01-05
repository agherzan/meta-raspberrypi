FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SPLASH_IMAGES:rpi = "file://psplash-raspberrypi-img.h;outsuffix=raspberrypi"

SRC_URI:append:rpi = " \
    file://framebuf.conf \
    file://fb.rules \
    "

do_install:append:rpi() {
    if [ "${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)}" ]; then
        install -Dm 0644 ${WORKDIR}/framebuf.conf ${D}${systemd_system_unitdir}/psplash-start.service.d/framebuf.conf
        install -d ${D}${sysconfdir}/udev/rules.d
        install -m 0644 ${WORKDIR}/fb.rules ${D}${sysconfdir}/udev/rules.d/
    fi
}

FILES:${PN}:append:rpi = " ${systemd_system_unitdir}/psplash-start.service.d"
