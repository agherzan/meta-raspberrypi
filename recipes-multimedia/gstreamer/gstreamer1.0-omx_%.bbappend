#
# Need to make this conditional to gstreamer1
#
SRC_URI_append_rpi = " \
             file://0001-config-files-path.patch \
             file://0002-Don-t-try-to-acquire-buffer-when-src-pad-isn-t-activ.patch \
             file://0003-fix-decoder-flushing.patch \
"

FILESEXTRAPATHS_prepend := "${THISDIR}/gstreamer1.0-omx:"

GSTREAMER_1_0_OMX_TARGET_rpi = "rpi"
GSTREAMER_1_0_OMX_CORE_NAME_rpi = "${libdir}/libopenmaxil.so"


# How to make this RPI specific?
EXTRA_OECONF_append_rpi  = " CFLAGS="$CFLAGS -I${STAGING_DIR_TARGET}/usr/include/IL -I${STAGING_DIR_TARGET}/usr/include/interface/vcos/pthreads -I${STAGING_DIR_TARGET}/usr/include/interface/vmcs_host/linux""
#examples only build with GL but not GLES, so disable it for RPI
EXTRA_OECONF_append_rpi = " --disable-examples"
