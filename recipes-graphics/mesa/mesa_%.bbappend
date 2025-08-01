FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

LIC_FILES_CHKSUM:rpi = "file://docs/license.rst;md5=ffe678546d4337b732cfd12262e6af11"

PACKAGECONFIG:append:rpi = " gallium gallium-llvm vc4 v3d ${@bb.utils.contains('DISTRO_FEATURES', 'x11 opengl', 'x11', '', d)} ${@bb.utils.contains('DISTRO_FEATURES', 'vulkan', 'vulkan broadcom', '', d)}"
DRIDRIVERS:class-target:rpi = ""

# Remove unused patches
SRC_URI:rpi = "https://mesa.freedesktop.org/archive/mesa-${PV}.tar.xz \
           file://0001-meson-misdetects-64bit-atomics-on-mips-clang.patch \
           file://0001-freedreno-don-t-encode-build-path-into-binaries.patch \
           file://0001-dont-build-clover-frontend.patch \
"

PV:rpi = "25.1.6"

python __anonymous() {
    if d.getVar('SOC_FAMILY') == 'rpi' and d.getVar("PN") == d.getVar("BPN"):
        d.setVarFlag("SRC_URI", "sha256sum", "9f2b69eb39d2d8717d30a9868fdda3e0c0d3708ba32778bbac8ddb044538ce84")
}

# DRI3 note:
# DRI3 Build option is removed from meson.
PACKAGECONFIG:remove:rpi = "dri3"

VULKAN_DRIVERS_AMD:rpi = "${@bb.utils.contains('PACKAGECONFIG', 'amd', ',amd', '', d)}"
VULKAN_DRIVERS_ASAHI:rpi = "${@bb.utils.contains('PACKAGECONFIG', 'asahi libclc opencl', ',asahi', '', d)}"
VULKAN_DRIVERS_INTEL:rpi = "${@bb.utils.contains('PACKAGECONFIG', 'intel libclc', ',intel', '', d)}"
VULKAN_DRIVERS_SWRAST:rpi = ",swrast"
# Crashes on x32
VULKAN_DRIVERS_SWRAST:x86-x32 = ""
VULKAN_DRIVERS_LLVM:rpi = "${VULKAN_DRIVERS_SWRAST}${VULKAN_DRIVERS_AMD}${VULKAN_DRIVERS_ASAHI}${VULKAN_DRIVERS_INTEL}"

EXTRA_OEMESON:remove:rpi = "-Ddri3=disabled"
EXTRA_OEMESON:remove:rpi = "-Dopencl-spirv=false"
# -Dglvnd is deprecated from true/false to enabled/disabled
EXTRA_OEMESON:remove:rpi = "-Dglvnd=false"
EXTRA_OEMESON:append:rpi = " -Dglvnd=disabled"

GALLIUMDRIVERS:rpi = "softpipe"

DEPENDS:append:rpi = " \
	${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'wayland-protocols', '', d)} \
	llvm \
	python3-pyyaml \
	python3-pyyaml-native \
"

RDEPENDS:libgl-mesa:rpi += " \
	llvm \
	${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'wayland-protocols', '', d)} \
"

PACKAGES:append:rpi = " \
             libgallium \
"

FILES:libgbm:rpi = "${libdir}/libgbm.so.* ${libdir}/gbm/*_gbm.so"
FILES:libgbm-dev:rpi = "${libdir}/libgbm.* ${libdir}/pkgconfig/gbm.pc ${includedir}/gbm.h ${includedir}/gbm_backend_abi.h"
FILES:libgallium:rpi = "${libdir}/libgallium-*.so"

FILES:libgl-mesa:append:rpi = " ${libdir}/libgallium*.so"

FILES:libgbm-dev:append:rpi = " ${includedir}/gbm.h"

# All DRI drivers are symlinks to libdril_dri.so
INSANE_SKIP:${PN}-megadriver += "dev-so"
