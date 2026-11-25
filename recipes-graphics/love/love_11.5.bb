SUMMARY = "Love application"
SECTION = "graphics"
LICENSE = "Zlib"
HOMEPAGE = "https://github.com/love2d/love"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Zlib;md5=87f239f408daca8a157858e192597633"
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

inherit autotools pkgconfig

SRC_URI = " \
    gitsm://github.com/love2d/love.git;protocol=https;branch=main;tag=${PV} \
    file://hello-world.lua \
"

do_configure:prepend() {
    bbnote "STAGING_DATADIR = ${STAGING_DATADIR}"
    bbnote "STAGING_BINDIR_NATIVE = ${STAGING_BINDIR_NATIVE}"
    bbnote "PATH = ${PATH}"
    bbnote "aclocal exists at: $(which aclocal 2>/dev/null || echo 'NOT FOUND')"
    bbnote "sdl2.m4 exists at: ${STAGING_DATADIR}/aclocal/sdl2.m4 ($(test -f "${STAGING_DATADIR}/aclocal/sdl2.m4" && echo 'FOUND' || echo 'NOT FOUND'))"

    unset prefix

    # Set ACLOCAL_PATH so automagic can find .m4 files
    export ACLOCAL_PATH="${STAGING_DATADIR}/aclocal:${STAGING_DATADIR_NATIVE}/aclocal"

    export CC="${CC}"
    export CXX="${CXX}"
    export LD="${CXX}"
    export CFLAGS="${CFLAGS} --sysroot=${STAGING_DIR_TARGET} ${TUNE_CCARGS}"
    export CXXFLAGS="${CXXFLAGS} --sysroot=${STAGING_DIR_TARGET} ${TUNE_CCARGS}"
    export LDFLAGS="${LDFLAGS} --sysroot=${STAGING_DIR_TARGET} -L${STAGING_DIR_TARGET}/usr/lib -L${STAGING_DIR_TARGET}/lib ${TUNE_CCARGS}"

    (cd ${S} && ./platform/unix/automagic)
}

do_install() {
    install -d ${D}${bindir}
    install -d ${D}${libdir}/love
    install -d ${D}${datadir}/love/examples
    install -d ${D}${datadir}/love/examples/hello-world

    # Install love binary to bindir
    install -m 0755 src/.libs/love ${D}${bindir}/love

    # Install libraries to libdir/love to avoid conflicts
    install -m 0644 src/.libs/lib* ${D}${libdir}/love/
    
    # Install lua script to examples directory (Linux tradition)
    install -m 0644 ${UNPACKDIR}/hello-world.lua ${D}${datadir}/love/examples/hello-world/main.lua
    
    # Fix the binary's dependencies: remove relative paths from NEEDED entries
    patchelf --remove-rpath ${D}${bindir}/love
    
    # Replace all NEEDED entries with ./.libs/ prefix with just the library name
    for lib in $(patchelf --print-needed ${D}${bindir}/love | grep '^\./.libs/'); do
        libname=$(basename $lib)
        patchelf --replace-needed $lib $libname ${D}${bindir}/love
    done
    
    patchelf --force-rpath --set-rpath ${libdir}/love ${D}${bindir}/love

    # Force the target's dynamic loader, whatever the linker chose during the build
    target_loader=$(basename $(ls ${STAGING_DIR_TARGET}/lib/ld-linux* | head -n1))
    patchelf --set-interpreter /lib/${target_loader} ${D}${bindir}/love
}

DEPENDS = "rsync autoconf-native automake-native libtool-native libsdl2 lua-native luajit freetype libmodplug libvorbis libogg libtheora openal-soft physfs mpg123 patchelf-native"

FILES:${PN} = "${bindir}/love ${libdir}/love/* ${datadir}/love/examples/hello-world ${datadir}/love/examples/hello-world/main.lua"