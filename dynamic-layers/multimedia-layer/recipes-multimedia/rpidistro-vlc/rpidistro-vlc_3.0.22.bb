DESCRIPTION = "Video player and streamer - davinci edition"
HOMEPAGE = "http://www.videolan.org"
SECTION = "multimedia"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "git://git@github.com/RPi-Distro/vlc;protocol=https;branch=pios/trixie \
           file://0001-configure-fix-linking-on-RISC-V-ISA.patch \
           file://0001-pi-util-Add-useful-scripts.patch \
           file://0002-Do-not-generate-cache-during-build.patch \
           file://0002-vlc-Upgrade-exit-timeout-to-exit-rather-than-signal.patch \
           file://0003-Bump-module-ABI-for-time_t-transition.patch \
           file://0003-vlc-Set-malloc-mmap-threshold-to-avoid-fragmentation.patch \
           file://0004-alsa-Add-HDMI-passthrough-for-TrueHD-DTS-etc.patch \
           file://0005-tospdif-Fix-DTS-rate-calculation-when-we-have-8-1920.patch \
           file://0006-avcodec-Add-AllocContextHw-to-try-v4l2m2-decoders-au.patch \
           file://0007-vlc_fourcc-Add-MMAL-picture-types.patch \
           file://0008-vlc_fourcc-Add-DRM_PRIME-picture-types.patch \
           file://0009-avcodec-va-Add-DRM_PRIME-swfmt-VLC-format-cases.patch \
           file://0010-avcodec-Fake-an-initial-timestamp-if-none-received.patch \
           file://0011-egl-add-queryDmaBufModifiersEXT-to-interface.patch \
           file://0013-configure.ac-Add-DRM-enable-option-test.patch \
           file://0014-configure.ac-Add-Wayland-single-pixel-capability-tes.patch \
           file://0015-configure.ac-Fix-lib-ordering-for-MMAL-libs.patch \
           file://0016-configure.ac-Add-mmal_avcodec-option.patch \
           file://0017-video_output-window-Add-lock-and-seq-for-wayland-sur.patch \
           file://0018-qt-interface_widgets-Use-window-locks-when-handle-mi.patch \
           file://0019-vlc_vout_window-Add-a-scaling-fraction-from-VLC-to-n.patch \
           file://0020-qt-interface-Set-scale-factor-in-vout_window.patch \
           file://0021-avcommon-Add-ffmpeg-trace-output-for-vvvv.patch \
           file://0022-qt-controller-Make-fullscreen-controller-a-popup-und.patch \
           file://0023-qt-Disable-1st-run-dialog.patch \
           file://0024-audio_output-When-padding-with-silence-add-zeros-in-.patch \
           file://0025-drm_pic-Add-DRM_PRIME-picture-type-code.patch \
           file://0026-mmal-Add-all-the-mmal-bits.patch \
           file://0027-caca-Set-driver-to-ncurses-avoiding-segfault.patch \
           file://0028-qt-Disable-HiDPIScaling-if-ratio-is-implausible.patch \
           file://0029-decoder-Add-a-vout_flush-to-decoder-shutdown.patch \
           file://0030-video_chroma-chain-Fix-chroma-resize-then-transform.patch \
           file://0031-video_output-Prioritise-GLES-over-GL.patch \
           file://0032-egl-Add-avcodec-DRM_PRIME-decode-path.patch \
           file://0033-egl-Require-dma_buf_import-support-if-using-egl-out.patch \
           file://0034-video_output-drmu-Add-DRM-video-output.patch \
           file://0035-video_output-wayland-Add-dmabuf-output.patch \
           file://0036-wayland-shm-Set-priority-to-0-to-use-wl-dmabuf-inste.patch \
           file://0037-xdg-shell-Rename-old-xdg-shall-include-files.patch \
           file://0038-wl_xdg_shell-Create-an-updated-version-of-xdg-shell.patch \
           file://0040-avcodec-Add-J420-formats-as-equivalent-to-YUV420-for.patch \
           file://0041-RPICHANGES-Add-change-log.patch \
           file://2001-fix-luaL-checkint.patch \
           file://2002-use-vorbisidec.patch \
           file://3001-configure.ac-setup-for-OE-usage.patch \
           file://3002-fix-EGL-macro-undeclared-and-EGLImageKHR.patch \
           file://3003-codec-omxil_core-replace-opt-vc-path-with-usr-lib.patch \
           file://3004-use-GLESv2-headers-over-GL-headers.patch \
           file://3005-modules-remove-glspectrum-usage.patch \
           ${@bb.utils.contains('DISTRO_FEATURES', 'x11', '', 'file://3007-remove-xorg-related-link-libs.patch', d)} \
           ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', '', 'file://3008-vo-Makefile.am-exclude-libgl_plugin.patch', d)} \
           file://3009-vo-converter_vaapi-Fix-EGL-macro-undeclared.patch \
           ${@bb.utils.contains('DISTRO_FEATURES', 'x11', '', 'file://3011-vout-drm-remove-xorg-releated-libs.patch', d)} \
           file://3012-Fix-initialization-of-atomic-variables-ATOMIC_VAR_IN.patch \
           file://3013-override-vlc-ver.patch \
           "

SRCREV = "1e4f72f9f7af4de546c90062c248f6174af69f28"

PROVIDES = "vlc"
RPROVIDES:${PN} = "${PROVIDES}"

DEPENDS = "coreutils-native fribidi libtool libgcrypt libgcrypt-native bison-native \
           dbus libxml2 gnutls \
           tremor faad2 ffmpeg flac alsa-lib \
           libidn \
           avahi jpeg xz libmodplug \
           libmtp libopus orc libsamplerate0 libusb1 schroedinger taglib \
           tiff"

inherit autotools-brokensep features_check gettext pkgconfig mime-xdg

export BUILDCC = "${BUILD_CC}"
EXTRA_OECONF = "\
    --enable-run-as-root \
    --enable-xvideo \
    --disable-screen --disable-caca \
    --enable-vlm \
    --enable-freetype \
    --enable-tremor \
    --enable-v4l2 --disable-aa --disable-faad \
    --enable-dbus \
    --without-contrib \
    --without-kde-solid \
    --enable-realrtsp \
    --enable-avcodec \
    ac_cv_path_MOC=${STAGING_BINDIR_NATIVE}${QT_DIR_NAME}/moc \
    ac_cv_path_RCC=${STAGING_BINDIR_NATIVE}${QT_DIR_NAME}/rcc \
    ac_cv_path_UIC=${STAGING_BINDIR_NATIVE}${QT_DIR_NAME}/uic \
"

PACKAGECONFIG ?= "\
    live555 dv1394 fontconfig fluidsynth freetype png \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'x11', '', d)} \
    x264 \
    ${@bb.utils.contains('MACHINE_FEATURES', 'vc4graphics', '', 'mmal', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'gles2', '', d)} \
    ${@bb.utils.contains_any('DISTRO_FEATURES', 'x11', 'notify', '', d)} \
    udev \
    alsa harfbuzz jack neon v4l2 drm \
    "
inherit ${@bb.utils.contains('PACKAGECONFIG', 'qt5', 'qmake5_paths', '', d)}

PACKAGECONFIG[mmal] = "--enable-omxil --enable-omxil-vout --enable-rpi-omxil --enable-mmal,,userland"
PACKAGECONFIG[x264] = "--enable-x264,--disable-x264,x264"
PACKAGECONFIG[fluidsynth] = ",,fluidsynth"
PACKAGECONFIG[mad] = "--enable-mad,--disable-mad,libmad"
PACKAGECONFIG[a52] = "--enable-a52,--disable-a52,liba52"
PACKAGECONFIG[jack] = "--enable-jack,--disable-jack,jack"
PACKAGECONFIG[live555] = "--enable-live555 LIVE555_PREFIX=${STAGING_DIR_HOST}${prefix},--disable-live555,live555"
PACKAGECONFIG[libass] = "--enable-libass,--disable-libass,libass"
PACKAGECONFIG[postproc] = "--enable-postproc,--disable-postproc,libpostproc"
PACKAGECONFIG[libva] = "--enable-libva,--disable-libva,libva"
PACKAGECONFIG[opencv] = "--enable-opencv,--disable-opencv,opencv"
PACKAGECONFIG[speex] = "--enable-speex,--disable-speex,speex"
PACKAGECONFIG[gstreamer] = "--enable-gst-decode,--disable-gst-decode,gstreamer1.0 gstreamer1.0-plugins-base gstreamer1.0-plugins-bad"
PACKAGECONFIG[vpx] = "--enable-vpx,--disable-vpx, libvpx"
PACKAGECONFIG[qt5] = "--enable-qt,--disable-qt, qtbase-native qtx11extras qtsvg"
PACKAGECONFIG[freerdp] = "--enable-freerdp,--disable-freerdp, freerdp"
PACKAGECONFIG[dvbpsi] = "--enable-dvbpsi,--disable-dvbpsi, libdvbpsi"
PACKAGECONFIG[samba] = "--enable-smbclient,--disable-smbclient, samba"
PACKAGECONFIG[upnp] = "--enable-upnp,--disable-upnp,libupnp"
PACKAGECONFIG[dvdnav] = "--enable-dvdnav,--disable-dvdnav,libdvdnav libdvdcss"
PACKAGECONFIG[sftp] = "--enable-sftp,--disable-sftp,libssh2"
PACKAGECONFIG[vorbis] = "--enable-vorbis,--disable-vorbis,libvorbis libogg"
PACKAGECONFIG[ogg] = "--enable-ogg,--disable-ogg,libvorbis libogg"
PACKAGECONFIG[dc1394] = "--enable-dc1394,--disable-dc1394,libdc1394"
PACKAGECONFIG[dv1394] = "--enable-dv1394,--disable-dv1394,libraw1394 libavc1394"
PACKAGECONFIG[svg] = "--enable-svg,--disable-svg,librsvg"
PACKAGECONFIG[svgdec] = "--enable-svgdec,--disable-svgdec,librsvg cairo"
PACKAGECONFIG[notify] = "--enable-notify,--disable-notify, libnotify"
PACKAGECONFIG[fontconfig] = "--enable-fontconfig,--disable-fontconfig, fontconfig"
PACKAGECONFIG[freetype] = "--enable-freetype,--disable-freetype, freetype"
PACKAGECONFIG[dvdread] = "--enable-dvdread,--disable-dvdread, libdvdread libdvdcss"
PACKAGECONFIG[vnc] = "--enable-vnc,--disable-vnc, libvncserver"
PACKAGECONFIG[x11] = "--with-x --enable-xcb,--without-x --disable-xcb,  xcb-util-keysyms libxpm libxinerama"
PACKAGECONFIG[png] = "--enable-png,--disable-png,libpng"
PACKAGECONFIG[vdpau] = "--enable-vdpau,--disable-vdpau,libvdpau"
PACKAGECONFIG[wayland] = "--enable-wayland,--disable-wayland,wayland wayland-native"


PACKAGECONFIG[gles2] = "--enable-gles2,--disable-gles2,virtual/libgles2"
PACKAGECONFIG[gnutls] = "--enable-gnutls,,gnutls"
PACKAGECONFIG[harfbuzz] = "--enable-harfbuzz,--disable-harfbuzz,harfbuzz"
PACKAGECONFIG[udev] = "--enable-udev,--disable-udev,udev"
PACKAGECONFIG[neon] = "--enable-neon,--disable-neon,"
PACKAGECONFIG[opus] = "--enable-opus,--disable-opus,libopus libogg"
PACKAGECONFIG[ncurses] = "--enable-ncurses,--disable-ncurses,ncurses"
PACKAGECONFIG[alsa] = "--enable-alsa,--disable-alsa,alsa-lib"
PACKAGECONFIG[pulseaudio] = "--enable-pulse,--disable-pulse,pulseaudio"
PACKAGECONFIG[sdl-image] = "--enable-sdl-image,,libsdl-image"
PACKAGECONFIG[v4l2] = "--enable-v4l2,,v4l-utils"
PACKAGECONFIG[drm] = "--enable-drm,--disable-drm,libdrm"
PACKAGECONFIG[lua] = "--enable-lua,--disable-lua,lua-native lua"

TARGET_CFLAGS:append = " -I${STAGING_INCDIR}/drm"
TARGET_LDFLAGS:append = " ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', '-lGLESv2', '', d)}"

# Ensures the --enable-mmal-avcodec flag is available for usage
do_configure:prepend() {
    olddir=`pwd`
    cd ${S}
    ./bootstrap
    cd $olddir 
}

do_configure:append() {
    # moc needs support: precreate build paths
    for qtpath in adapters components/epg components/playlist components/sout dialogs managers styles util/buttons; do
        mkdir -p "${B}/modules/gui/qt/$qtpath"
    done
    sed -i -e 's|${WORKDIR}||g' ${B}/config.h
}

# This recipe packages vlc as a library as well, so qt4 dependencies
# can be avoided when only the library is installed.
PACKAGES =+ "libvlc"

LEAD_SONAME_libvlc = "libvlc.so.5"
FILES:libvlc = "${libdir}/lib*.so.*"

FILES:${PN} += "\
    ${bindir}/vlc \
    ${libdir}/vlc \
    ${datadir}/applications \
    ${datadir}/vlc \
    ${datadir}/icons \
    ${datadir}/metainfo/org.videolan.vlc.appdata.xml \
    "

FILES:${PN}-dbg += "\
    ${libdir}/vlc/*/.debug \
    ${libdir}/vlc/plugins/*/.debug \
    "

FILES:${PN}-staticdev += "\
    ${libdir}/vlc/plugins/*/*.a \
    ${libdir}/vlc/libcompat.a \
    "

# Only enable it for rpi class of machines
COMPATIBLE_HOST = "null"
COMPATIBLE_HOST:rpi = "(.*)"

INSANE_SKIP:${PN} = "dev-so"

INSANE_SKIP:libvlc += "buildpaths"