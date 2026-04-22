# Base this image on core-image-base
include recipes-core/images/core-image-base.bb

COMPATIBLE_MACHINE = "^rpi$"

IMAGE_INSTALL:append = " systemd systemd-analyze systemd-boot packagegroup-rpi-test"
VIRTUAL-RUNTIME_initscripts = ""
