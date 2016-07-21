EXTRA_OECONF_append_rpi = "${@bb.utils.contains('MACHINE_FEATURES', 'vc4graphics', '', ' --enable-rpi-compositor WESTON_NATIVE_BACKEND=rpi-backend.so', d)}"

