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

## Images

* rpi-test-image
  * Image based on core-image-base which includes most of the packages in this
    layer and some media samples.

For other uses it's recommended to base images on `core-image-minimal` or
`core-image-base` as appropriate. The old image names (`rpi-hwup-image` and
`rpi-basic-image`) are deprecated.
