# meta-raspberrypi

Yocto BSP layer for the Raspberry Pi boards - http://www.raspberrypi.org/ .

[![Build Status](https://yocto-ci.resin.io/job/meta-raspberrypi1/badge/icon)](https://yocto-ci.resin.io/job/meta-raspberrypi1)
[![Build Status](https://yocto-ci.resin.io/job/meta-raspberrypi2/badge/icon)](https://yocto-ci.resin.io/job/meta-raspberrypi2)
[![Build Status](https://yocto-ci.resin.io/job/meta-raspberrypi3/badge/icon)](https://yocto-ci.resin.io/job/meta-raspberrypi3)
[![Gitter chat](https://badges.gitter.im/gitterHQ/gitter.png)](https://gitter.im/agherzan/meta-raspberrypi)

## Quick links
* Git repository web frontend: http://git.yoctoproject.org/cgit/cgit.cgi/meta-raspberrypi/
* Mailing list (yocto mailing list): yocto@yoctoproject.org
* Issues management (Github Issues): https://github.com/agherzan/meta-raspberrypi/issues

## Contents
```
1. Description
2. Yocto BSP Layer - Raspberry Pi
    2.A. How to use it
    2.B. Images
3. Optional build configuration
    3.A. Compressed deployed files
    3.B. GPU memory
    3.C. Add purchased license codecs
    3.D. Disable overscan
    3.E. Set overclocking options
    3.F. Video camera support with V4L2 drivers
    3.G. Enable offline compositing support
    3.H. Enable kgdb over console support
    3.I. Boot to U-Boot
    3.J. Image with Initramfs
    3.K. Device tree support
    3.L. Enable SPI bus
    3.M. Enable I2C
    3.N. Enable PiTFT support
    3.O. Misc. display
    3.P. Enable UART support
4. Extra apps
    4.A. omxplayer
5. Board Configuration
    5.A. Audio Routing
6. Source code and mirrors
7. Contribution
    7.A. Mailing List
    7.B. Github Issues
8. Maintainers
```

### 1. Description
This is the general hardware specific BSP overlay for the RaspberryPi device.
More information can be found at: http://www.raspberrypi.org/ (Official Site)
The core BSP part of meta-raspberrypi should work with different OpenEmbedded/Yocto distributions and layer stacks, such as:
* Distro-less (only with OE-Core).
* Angstrom.
* Yocto/Poky (main focus of testing).

### 2. Yocto BSP Layer - RaspberryPi
This layer depends on:
* URI: git://git.yoctoproject.org/poky
  * branch: master
  * revision: HEAD
* URI: git://git.openembedded.org/meta-openembedded
  * layers: meta-oe, meta-multimedia, meta-networking, meta-python
  * branch: master
  * revision: HEAD

#### 2.A. How to use it
1. source poky/oe-init-build-env rpi-build
2. Add this layer to bblayers.conf and dthe dependencies above
3. Set MACHINE in local.conf to one of the supported boards:
  * raspberrypi
   * raspberrypi0
   * raspberrypi0-wifi
   * raspberrypi2
   * raspberrypi3
   * raspberrypi3-64 (64 bit kernel & userspace)
   * raspberrypi-cm (dummy alias for raspberrypi)
   * raspberrypi-cm3 (dummy alias for raspberrypi2)
4. bitbake rpi-hwup-image
5. dd to a SD card the generated sdimg file (use xzcat if rpi-sdimg.xz is used)
6. Boot your RPI.

#### 2.B. Images
* rpi-hwup-image
  * Hardware up image
* rpi-basic-image
  * Based on rpi-hwup-image with some added features (ex: splash)
* rpi-test-image
  * Image based on rpi-basic-image which includes most of the packages in this layer and some media samples.

### 3. Optional build configuration
There are a set of ways in which a user can influence different paramenters of the build. We list here the ones that are closely related to this BSP or specific to it. For the rest please check: http://www.yoctoproject.org/docs/latest/ref-manual/ref-manual.html

#### 3.A. Compressed deployed files
1. Overwrite IMAGE_FSTYPES in local.conf
  * `IMAGE_FSTYPES = "tar.bz2 ext3.xz"`
2. Overwrite SDIMG_ROOTFS_TYPE in local.conf
  * `SDIMG_ROOTFS_TYPE = "ext3.xz"`
3. Overwrite SDIMG_COMPRESSION in local.conf
  * `SDIMG_COMPRESSION = "xz"`

Accommodate the values above to your own needs (ex: ext3 / ext4).

#### 3.B. GPU memory
| Variable       | Details                                                             |
|----------------|---------------------------------------------------------------------|
| `GPU_MEM`      | GPU memory in megabyte. Sets the memory split between the ARM and   |
|                | GPU. ARM gets the remaining memory. Min 16. Default 64.             |
| `GPU_MEM_256`  | GPU memory in megabyte for the 256MB Raspberry Pi. Ignored by the   |
|                | 512MB RP. Overrides gpu_mem. Max 192. Default not set.              |
| `GPU_MEM_512`  | GPU memory in megabyte for the 512MB Raspberry Pi. Ignored by the   |
|                | 256MB RP. Overrides gpu_mem. Max 448. Default not set.              |
| `GPU_MEM_1024` | GPU memory in megabyte for the 1024MB Raspberry Pi. Ignored by the  |
|                | 256MB/512MB RP. Overrides gpu_mem. Max 944. Default not set.        |

#### 3.C. Add purchased license codecs
To add you own licenses use variables `KEY_DECODE_MPG2` and `KEY_DECODE_WVC1` in local.conf. Example:
```
KEY_DECODE_MPG2 = "12345678"
KEY_DECODE_WVC1 = "12345678"
```
You can supply more licenses separated by comma. Example:
```
KEY_DECODE_WVC1 = "0x12345678,0xabcdabcd,0x87654321"
```

#### 3.D. Disable overscan
By default the GPU adds a black border around the video output to compensate for TVs which cut off part of the image. To disable this set this variable in local.conf:
`DISABLE_OVERSCAN = "1"`

#### 3.E. Set overclocking options
The Raspberry PI can be overclocked. As of now overclocking up to the "Turbo Mode" is officially supported by the raspbery and does not void warranty. Check the config.txt for a detailed description of options and modes. Example turbo mode:
```
ARM_FREQ = "1000"
CORE_FREQ = "500"
SDRAM_FREQ = "500"
OVER_VOLTAGE = "6"
```

#### 3.F. Video camera support with V4L2 drivers
Set this variable to enable support for the video camera (Linux 3.12.4+ required)
`VIDEO_CAMERA = "1"`

#### 3.G. Enable offline compositing support
Set this variable to enable support for dispmanx offline compositing:
`DISPMANX_OFFLINE = "1"`

This will enable the firmware to fall back to off-line compositing of Dispmanx elements. Normally the compositing is done on-line, during scanout, but cannot handle too many elements. With off-line enabled, an off-screen buffer is allocated for compositing. When scene complexity (number and sizes
of elements) is high, compositing will happen off-line into the buffer.

Heavily recommended for Wayland/Weston.

See: http://wayland.freedesktop.org/raspberrypi.html

#### 3.H. Enable kgdb over console support
To add the kdbg over console (kgdboc) parameter to the kernel command line, set this variable in local.conf:
`ENABLE_KGDB = "1"`

#### 3.I. Boot to U-Boot
To have u-boot load kernel image, set in your local.conf:
`KERNEL_IMAGETYPE = "uImage"`

This will make kernel.img be u-boot image which will load uImage. By default, kernel.img is the actual kernel image (ex. Image).

#### 3.J. Image with Initramfs
To build an initramfs image :
* Set this 3 kernel variables (in linux-raspberrypi.inc for example)
  - kernel_configure_variable BLK_DEV_INITRD y
  - kernel_configure_variable INITRAMFS_SOURCE ""
  - kernel_configure_variable RD_GZIP y
* Set the yocto variables (in linux-raspberrypi.inc for example)
  - `INITRAMFS_IMAGE = "<a name for your initramfs image>"`
  - `INITRAMFS_IMAGE_BUNDLE = "1"`
* Set the meta-rasberrypi variable (in raspberrypi.conf for example)
  - `KERNEL_INITRAMFS = "-initramfs"`

#### 3.K. Device tree support
Device tree for RPi is only supported when using linux-raspberrypi 3.18+ kernels.
* Set `KERNEL_DEVICETREE` (in conf/machine/raspberrypi.conf)
  - the trailer is added to the kernel image before kernel install task. While creating the SDCard image, this modified kernel is put on boot partition (as kernel.img) as well as DeviceTree blobs (.dtb files).

NOTE: `KERNEL_DEVICETREE` is default enabled for kernel >= 3.18 and always disabled for
      older kernel versions.

#### 3.L. Enable SPI bus
When using device tree kernels, set this variable to enable the SPI bus:
`ENABLE_SPI_BUS = "1"`

#### 3.M. Enable I2C
When using device tree kernels, set this variable to enable I2C:
`ENABLE_I2C = "1"`

#### 3.N. Enable PiTFT support
Basic support for using PiTFT screens can be enabled by adding below in local.conf:

* `MACHINE_FEATURES += "pitft"`
  - This will enable SPI bus and i2c device-trees, it will also setup framebuffer for console and x server on PiTFT.

NOTE: To get this working the overlay for the PiTFT model must be build, added and specified as well (dtoverlay=<driver> in config.txt).

Below is a list of currently supported PiTFT models in meta-raspberrypi, the modelname should be added as a MACHINE_FEATURES in local.conf like below:
`MACHINE_FEATURES += "pitft <modelname>"`

List of currently supported models:
* pitft22
* pitft28r
* pitft35r

#### 3.O. Misc. display
If you would like to use the Waveshare "C" 1024×600, 7 inch Capacitive Touch Screen LCD, HDMI interface (http://www.waveshare.com/7inch-HDMI-LCD-C.htm) Rev 2.1, please set the following in your local.conf:
`WAVESHARE_1024X600_C_2_1 = "1"`

#### 3.P. Enable UART
RaspberryPi 0, 1, 2 and CM will have UART console enabled by default.

RaspberryPi 0 WiFi and 3 does not have the UART enabled by default because this needs a fixed core frequency and enable_uart wil set it to the minimum. Certain operations - 60fps h264 decode, high quality deinterlace - which aren't performed on the ARM may be affected, and we wouldn't want to do that to users
who don't want to use the serial port. Users who want serial console support on RaspberryPi3 will have to explicitely set in local.conf: `ENABLE_UART = "1"`.

Ref.:
* https://github.com/raspberrypi/firmware/issues/553
* https://github.com/RPi-Distro/repo/issues/22

### 4. Extra apps

#### 4.A. omxplayer
omxplayer depends on libav which has a commercial license. So in order to be able to compile omxplayer you will need to whiteflag the commercial licenseadding to you local.conf:
`LICENSE_FLAGS_WHITELIST = "commercial"`

### 5. Board Configuration

#### 5.A. Audio Routing
To load audio driver
`modprobe snd-bcm2835`
To test audio playback
`aplay test.wav`

Note that without HDMI connected this emits audio from the 3.5in jack connector as expected. However With an HDMI display connected there is no audio output from the jack connector.

To force the audio routing via the 3.5in jack connector use
`amixer cset numid=3 1`

Options to amixer cset are:
```
0=auto
1=headphones
2=hdmi
```

### 6. Source code and mirrors

Main repo:
* git://git.yoctoproject.org/meta-raspberrypi
* http://git.yoctoproject.org/git/meta-raspberrypi

Github mirror:
* https://github.com/agherzan/meta-raspberrypi

Bitbucket mirror:
* https://bitbucket.org/agherzan/meta-raspberrypi


### 7. Contributing

#### 7.A. Mailing list
The main communication tool we use is a mailing list:
* yocto@yoctoproject.org
* https://lists.yoctoproject.org/listinfo/yocto

Feel free to ask any kind of questions but always prepend your email subject with "[meta-raspberrypi]". This is because we use the 'yocto' mailing list and not a perticular 'meta-raspberrypi' mailing list.

To contribute to this layer you should send the patches for review to the above specified mailing list.
The patches should be compliant with the openembedded patch guidelines: http://www.openembedded.org/wiki/Commit_Patch_Message_Guidelines


When creating patches, please use something like:
`git format-patch -s --subject-prefix='meta-raspberrypi][PATCH' origin`

When sending patches to mailing list, please use something like:
`git send-email --to yocto@yoctoproject.org <generated patch>`

#### 7.B. Github issues
In order to manage and trace the meta-raspberrypi issues, we use github issues: https://github.com/agherzan/meta-raspberrypi/issues

If you push patches which have a github issue associated, please provide the issue number in the commit log just before "Signed-off-by" line(s). Example line for a bug:
`[Issue #13]`

### 8. Maintainers
* Andrei Gherzan `<andrei at gherzan.ro>`
