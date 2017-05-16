FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.4.6"

SRCREV = "8eacc83e68c1f2b78b38d62fe5956a6170dd0011"
SRC_URI = "git://github.com/giraldeau/linux.git;protocol=git;branch=linux-4.4.y-rt-rebase-rpi \
           file://0001-dts-add-overlay-for-pitft22.patch \
           file://rt.cfg \
"

require linux-raspberrypi.inc

kernel_configure_variable() {
    # Remove the config
    CONF_SED_SCRIPT="$CONF_SED_SCRIPT /CONFIG_$1[ =]/d;"
    if test "$2" = "n"
    then
        echo "# CONFIG_$1 is not set" >> ${B}/.config
    else
        echo "CONFIG_$1=$2" >> ${B}/.config
    fi
}

do_configure_prepend() {
    CONF_SED_SCRIPT=""

    kernel_configure_variable UPROBES y

    sed -e "${CONF_SED_SCRIPT}" < '${WORKDIR}/defconfig' >> '${B}/.config'
    yes '' | oe_runmake oldconfig
}

