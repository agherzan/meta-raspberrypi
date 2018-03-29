# Layer Contents

## Supported Machines

* raspberrypi
* raspberrypi0
* raspberrypi0-wifi
* raspberrypi2
* raspberrypi3
* raspberrypi3-64 (64 bit kernel & userspace)
* raspberrypi-cm (dummy alias for raspberrypi)
* raspberrypi-cm3 (dummy alias for raspberrypi2)

Note: The raspberrypi3 machines include support for Raspberry Pi 3B+.

## Images

* rpi-test-image
  * Image based on core-image-base which includes most of the packages in this
    layer and some media samples.

For other uses it's recommended to base images on `core-image-minimal` or
`core-image-base` as appropriate. The old image names (`rpi-hwup-image` and
`rpi-basic-image`) are deprecated.

## WiFi firmware blobs

Be aware that the WiFi firmwares for the supported boards are not provided by
`linux-firmware` but by a custom recipe which only packages the needed blobs
for these boards. This is because the upstream `linux-firmware` doesn't support
or has outdated files for the blobs we need. The recipe
`linux-firmware-raspbian` is based on a fork of `linux-firmware` which
includes everything we need in order to fully support the WiFi hardware. All
machines define `MACHINE_EXTRA_RRECOMMENDS` accordingly.
