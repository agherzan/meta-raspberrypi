DEFAULTTUNE ?= "armv6"

require conf/machine/include/arm/arch-armv6.inc

TUNEVALID[arm1176jzfs] = "Enable arm1176jzfs specific processor optimizations"
TUNE_CCARGS += "${@bb.utils.contains("TUNE_FEATURES", "arm1176jzfs", "-mtune=arm1176jzf-s", "", d)}"

AVAILTUNES += "arm1176jzfs"
TUNE_FEATURES_tune-arm1176jzfs = "${TUNE_FEATURES_tune-armv6} arm1176jzfs"
ARMPKGARCH_tune-arm1176jzfs = "arm1176jzfs"
PACKAGE_EXTRA_ARCHS_tune-arm1176jzfs = "${PACKAGE_EXTRA_ARCHS_tune-armv6}"
