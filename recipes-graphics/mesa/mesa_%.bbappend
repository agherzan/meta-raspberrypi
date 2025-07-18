FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

LIC_FILES_CHKSUM = "file://docs/license.rst;md5=ffe678546d4337b732cfd12262e6af11"

PACKAGECONFIG:append:rpi = " gallium gallium-llvm vc4 v3d ${@bb.utils.contains('DISTRO_FEATURES', 'x11 opengl', 'x11', '', d)} ${@bb.utils.contains('DISTRO_FEATURES', 'vulkan', 'vulkan broadcom', '', d)}"
DRIDRIVERS:class-target:rpi = ""

# Remove unused patches
SRC_URI = "https://mesa.freedesktop.org/archive/mesa-${PV}.tar.xz \
           file://0001-meson-misdetects-64bit-atomics-on-mips-clang.patch \
           file://0001-freedreno-don-t-encode-build-path-into-binaries.patch \
           file://0001-dont-build-clover-frontend.patch \
"

SRC_URI[sha256sum] = "9f2b69eb39d2d8717d30a9868fdda3e0c0d3708ba32778bbac8ddb044538ce84"
PV = "25.1.6"

# -Dglvnd is deprecated from true/false to enabled/disabled 
PACKAGECONFIG[glvnd] = "-Dglvnd=enabled, -Dglvnd=disabled, libglvnd"

# DRI3 note:
# DRI3 Build option is removed from meson.
PACKAGECONFIG:remove = "dri3"
unset PACKAGECONFIG[dri3]

unset VULKAN_DRIVERS
VULKAN_DRIVERS_AMD = "${@bb.utils.contains('PACKAGECONFIG', 'amd', ',amd', '', d)}"
VULKAN_DRIVERS_ASAHI = "${@bb.utils.contains('PACKAGECONFIG', 'asahi libclc opencl', ',asahi', '', d)}"
VULKAN_DRIVERS_INTEL = "${@bb.utils.contains('PACKAGECONFIG', 'intel libclc', ',intel', '', d)}"
VULKAN_DRIVERS_SWRAST = ",swrast"
# Crashes on x32
VULKAN_DRIVERS_SWRAST:x86-x32 = ""
VULKAN_DRIVERS_LLVM = "${VULKAN_DRIVERS_SWRAST}${VULKAN_DRIVERS_AMD}${VULKAN_DRIVERS_ASAHI}${VULKAN_DRIVERS_INTEL}"

VULKAN_DRIVERS = ""
VULKAN_DRIVERS:append = "${@bb.utils.contains('PACKAGECONFIG', 'freedreno', ',freedreno', '', d)}"
VULKAN_DRIVERS:append = "${@bb.utils.contains('PACKAGECONFIG', 'broadcom', ',broadcom', '', d)}"
VULKAN_DRIVERS:append = "${@bb.utils.contains('PACKAGECONFIG', 'gallium-llvm', '${VULKAN_DRIVERS_LLVM}', '', d)}"
VULKAN_DRIVERS:append = "${@bb.utils.contains('PACKAGECONFIG', 'imagination', ',imagination-experimental', '', d)}"
VULKAN_DRIVERS:append = "${@bb.utils.contains('PACKAGECONFIG', 'panfrost', ',panfrost', '', d)}"
PACKAGECONFIG[vulkan] = "-Dvulkan-drivers=${@strip_comma('${VULKAN_DRIVERS}')}, -Dvulkan-drivers='',glslang-native vulkan-loader vulkan-headers"

GALLIUMDRIVERS = "softpipe"

PACKAGECONFIG[opencl] = "-Dgallium-opencl=icd,-Dgallium-opencl=disabled,"

DEPENDS += " wayland-protocols llvm python3-pyyaml python3-pyyaml-native"

RDEPENDS:libgl-mesa += " llvm wayland-protocols"

PACKAGES =+ " \
             libgallium \
"

FILES:libgbm = "${libdir}/libgbm.so.* ${libdir}/gbm/*_gbm.so"
FILES:libgbm-dev = "${libdir}/libgbm.* ${libdir}/pkgconfig/gbm.pc ${includedir}/gbm.h ${includedir}/gbm_backend_abi.h"
FILES:libgallium = "${libdir}/libgallium-*.so"

FILES:libgl-mesa += " ${libdir}/libgallium*.so"

FILES:libgbm-dev += " ${includedir}/gbm.h"

# All DRI drivers are symlinks to libdril_dri.so
INSANE_SKIP:${PN}-megadriver += "dev-so"
