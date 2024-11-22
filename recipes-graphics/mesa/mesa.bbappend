PACKAGECONFIG:append:rpi = " gallium gallium-llvm vc4 v3d ${@bb.utils.contains('DISTRO_FEATURES', 'x11 opengl', 'x11', '', d)} ${@bb.utils.contains('DISTRO_FEATURES', 'vulkan', 'vulkan broadcom', '', d)}"
DRIDRIVERS:class-target:rpi = ""

# Remove unused patches
SRC_URI = "https://mesa.freedesktop.org/archive/mesa-${PV}.tar.xz \
           file://0001-meson-misdetects-64bit-atomics-on-mips-clang.patch \
"

SRC_URI[sha256sum] = "97813fe65028ef21b4d4e54164563059e8408d8fee3489a2323468d198bf2efc"
PV = "24.3.0"

# -Dglvnd is deprecated from true/false to enabled/disabled 
PACKAGECONFIG[glvnd] = "-Dglvnd=enabled, -Dglvnd=disabled, libglvnd"

# DRI3 note:
# DRI3 Build option is removed from meson.
PACKAGECONFIG_CONFARGS:remove = "-Ddri3=enabled, -Ddri3=disabled"

DEPENDS += " wayland-protocols llvm python3-pyyaml python3-pyyaml-native"

RDEPENDS:libgl-mesa += " llvm wayland-protocols"

FILES:libgbm += " ${libdir}/gbm/dri_gbm*.so"

FILES:libgl-mesa += " ${libdir}/libgallium*.so"

FILES:libgbm-dev += " ${includedir}/gbm.h"
