EXTRA_OECONF_append_raspberrypi = " CPPFLAGS='-I${STAGING_DIR_TARGET}/usr/include/interface/vcos/pthreads \
                                   -I${STAGING_DIR_TARGET}/usr/include/interface/vmcs_host/linux' \
                                    --with-egl-window-system=rpi"
