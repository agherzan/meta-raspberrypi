DESCRIPTION = "OMXPlayer is a commandline OMX player for the Raspberry Pi"
HOMEPAGE = "https://github.com/huceke/omxplayer"
SECTION = "console/utils"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

DEPENDS = "libpcre libav virtual/egl boost freetype"

PR = "r0"

SRCREV = "231c08b42005e3de565013bc1cee18bd5a349c1f"
SRC_URI = "git://github.com/huceke/omxplayer.git;protocol=git;branch=master \
           file://remove-makefile-include.patch \
           file://libraries-are-installed-in-usr-lib.patch \
           file://remove-hardcoded-directory-omxplayer-dist.patch \
           file://don-t-strip-while-installing.patch \
          "
S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "raspberrypi"

inherit autotools

# Variable added in Makefile to INCLUDE
export ADD_INCDIR = "-I${STAGING_INCDIR}/interface/vcos/pthreads -I${STAGING_INCDIR}/freetype2"

# Needed in configure from Makefile.ffmpeg
export HOST = "${HOST_SYS}"
export WORK = "${S}"
export TEMPDIR = "${S}/tmp"
export FLOAT = "softfp"

export LDFLAGS = "-L${S}/ffmpeg_compiled/usr/lib \
                  -L${STAGING_DIR_HOST}/lib \
                  -L${STAGING_DIR_HOST}/usr/lib \
                 "

export INCLUDES = "-isystem${STAGING_DIR_HOST}/usr/include \
                   -isystem${STAGING_DIR_HOST}/usr/include/interface/vcos/pthreads \
                   -isystem${STAGING_DIR_HOST}/usr/include/freetype2 \
                  "

# Install in ${D}
export DEST = "${D}"

do_compile() {
	# Needed for compiler test in ffmpeg's configure
	mkdir -p tmp

	oe_runmake ffmpeg
	oe_runmake
}

do_install() {
	oe_runmake dist
}

FILES_${PN} = "${bindir}/omxplayer* \
               ${libdir}/omxplayer/lib*${SOLIBS}"

FILES_${PN}-dev += "${libdir}/omxplayer/*.so"
