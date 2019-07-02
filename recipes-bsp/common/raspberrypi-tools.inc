SRCREV ?= "7f4a937e1bacbc111a22552169bc890b4bb26a94"
RPITOOLS_SRC_URI ?= "https://github.com/raspberrypi/tools/archive/${SRCREV}.tar.gz"
RPITOOLS_S ?= "${WORKDIR}/tools-${SRCREV}"

SRC_URI = "${RPITOOLS_SRC_URI}"
SRC_URI[md5sum] = "f03672c57a4f285d0583ba7028f46a83"
SRC_URI[sha256sum] = "85e884cd95207780bc03fc7dacea1f17d2462c1e8c7499b0ff30070ca1d4d48a"

PV = "0.0+git${SRCREV}"
