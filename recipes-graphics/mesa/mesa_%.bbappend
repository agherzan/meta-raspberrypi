PACKAGECONFIG:append:rpi = " gallium gallium-llvm vc4 v3d ${@bb.utils.contains('DISTRO_FEATURES', 'x11 opengl', 'x11', '', d)} ${@bb.utils.contains('DISTRO_FEATURES', 'vulkan', 'vulkan broadcom', '', d)}"
DRIDRIVERS:class-target:rpi = ""

# Remove unused patches
SRC_URI = "https://mesa.freedesktop.org/archive/mesa-${PV}.tar.xz \
           file://0001-meson-misdetects-64bit-atomics-on-mips-clang.patch \
"

SRC_URI[sha256sum] = "9c795900449ce5bc7c526ba0ab3532a22c3c951cab7e0dd9de5fcac41b0843af"
PV = "24.3.1"

# -Dglvnd is deprecated from true/false to enabled/disabled 
PACKAGECONFIG[glvnd] = "-Dglvnd=enabled, -Dglvnd=disabled, libglvnd"

# DRI3 note:
# DRI3 Build option is removed from meson.
PACKAGECONFIG:remove = "dri3"
unset PACKAGECONFIG[dri3]

DEPENDS += " wayland-protocols llvm python3-pyyaml python3-pyyaml-native"

RDEPENDS:libgl-mesa += " llvm wayland-protocols"

FILES:libgbm += " ${libdir}/gbm/dri_gbm*.so"

FILES:libgl-mesa += " ${libdir}/libgallium*.so"

FILES:libgbm-dev += " ${includedir}/gbm.h"
