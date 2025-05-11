PACKAGECONFIG:append:rpi = " gallium vc4 v3d ${@bb.utils.contains('DISTRO_FEATURES', 'x11 opengl', 'x11', '', d)} ${@bb.utils.contains('DISTRO_FEATURES', 'vulkan', 'vulkan broadcom', '', d)}"
DRIDRIVERS:class-target:rpi = ""
