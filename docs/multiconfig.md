<!--
SPDX-FileCopyrightText: Andrei Gherzan <andrei.gherzan@huawei.com>

SPDX-License-Identifier: CC-BY-4.0
-->

# Multiconfig Support

The build system provides functionality for building multiple configurations in
the same build invocation while also supporting dependencies between them. For
more info check the
[official documentation](https://docs.yoctoproject.org/dev-manual/common-tasks.html#building-images-for-multiple-targets-using-multiple-configurations).
`meta-raspberrypi` uses this to provide the ability to generate images that can
boot on multiple targets (for example, being able to boot either a Raspberry Pi
3 or a Raspberry Pi 4 in 32bit mode using the same image). This is in contrast
to the classic builds where the `MACHINE` defines the only target intended for
the final image artefact.

## Generating an Image For Multiple Raspberry Pi Boards

Once you've set up a build environment, the multiconfigs need to be enabled
adding to the build's `local.conf` the following:

    BBMULTICONFIG += "raspberrypi raspberrypi3"

With that configured, any image can now be build using the `raspberrypi`
multiconfig.  Here is an example that will build the `core-image-base`:

    bitbake mc:raspberrypi:core-image-base

The final image will be available in:

    <BUILDDIR>/tmp-multi-raspberrypi/deploy/images/raspberrypi4/

This image will boot and provide the support for all the machines listed below.

## Supported Machines for Multiconfig Builds

* raspberrypi3
* raspberrypi4
