# RaspberryPi BSP default providers

PREFERRED_PROVIDER_virtual/kernel = "linux-raspberrypi"
PREFERRED_PROVIDER_virtual/xserver = "xserver-xorg"
PREFERRED_PROVIDER_virtual/egl ?= "vc-graphics-hardfp"
PREFERRED_PROVIDER_virtual/libgles2 ?= "vc-graphics-hardfp"
PREFERRED_PROVIDER_linux-libc-headers ?= "linux-libc-headers-raspberrypi"
