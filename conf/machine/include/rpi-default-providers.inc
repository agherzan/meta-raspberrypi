# RaspberryPi BSP default providers

PREFERRED_PROVIDER_virtual/kernel = "linux-raspberrypi"
PREFERRED_VERSION_linux-raspberrypi ?= "3.10.%"

PREFERRED_PROVIDER_u-boot = "u-boot-rpi"

PREFERRED_PROVIDER_virtual/xserver = "xserver-xorg"
PREFERRED_PROVIDER_virtual/egl ?= "userland"
PREFERRED_PROVIDER_virtual/libgles2 ?= "userland"
PREFERRED_PROVIDER_jpeg = "jpeg"
