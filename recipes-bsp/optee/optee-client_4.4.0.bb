require optee-client.inc

SRCREV = "d221676a58b305bddbf97db00395205b3038de8e"
PLATFORM = "rpi3"

inherit pkgconfig

DEPENDS += "util-linux"
EXTRA_OEMAKE += "PKG_CONFIG=pkg-config"

FILES:${PN} = " \
                /etc/init.d/tee-supplicant \
                /etc/udev/rules.d/optee-udev.rules \
                /lib/systemd/system/tee-supplicant.service \
                /usr/include/* \
                /usr/lib/* \
                /usr/lib/pkgconfig/* \
                /usr/lib/systemd/system/* \
                /usr/sbin/tee-supplicant \
                "