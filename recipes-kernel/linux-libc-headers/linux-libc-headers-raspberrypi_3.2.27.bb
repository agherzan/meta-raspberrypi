require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

PR = "r0"

PROVIDES = "linux-libc-headers"
RPROVIDES_${PN}-dev = "linux-libc-headers-dev"
RPROVIDES_${PN}-dbg = "linux-libc-headers-dbg"

SRCREV = "10182a3bc434b27740f81c2b836a1af943060241"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-3.2.27 \
          "
S = "${WORKDIR}/git"
