# Base this image on rpi-hwup-image
include rpi-hwup-image.bb

IMAGE_FEATURES += "ssh-server-dropbear splash"
