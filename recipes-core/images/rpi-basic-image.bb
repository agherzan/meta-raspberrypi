# Base this image on rpi-hwup-image
include rpi-hwup-image.bb

SPLASH = "psplash-raspberrypi"

IMAGE_FEATURES += "ssh-server-dropbear splash"

do_image_prepend() {
    bb.warn("The image 'rpi-basic-image' is deprecated, please use 'core-image-base' instead")
}
