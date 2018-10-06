GSTREAMER_1_0_OMX_TARGET_rpi = "${@bb.utils.contains("MACHINE_FEATURES", "vc4graphics", "bellagio", "rpi", d)}"
GSTREAMER_1_0_OMX_CORE_NAME_rpi = "${@bb.utils.contains("MACHINE_FEATURES", "vc4graphics", "", "${libdir}/libopenmaxil.so", d)}"


# How to make this RPI specific?
EXTRA_OECONF_append_rpi  = " CFLAGS="$CFLAGS -I${STAGING_DIR_TARGET}/usr/include/IL -I${STAGING_DIR_TARGET}/usr/include/interface/vcos/pthreads -I${STAGING_DIR_TARGET}/usr/include/interface/vmcs_host/linux""
#examples only build with GL but not GLES, so disable it for RPI
EXTRA_OECONF_append_rpi = " --disable-examples"

