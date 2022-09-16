# SPDX-FileCopyrightText: meta-raspberrypi contributors
#
# SPDX-License-Identifier: MIT

PACKAGECONFIG:append:rpi = " gbm"
PROVIDES:append:rpi = " virtual/libgbm"

GALLIUMDRIVERS:append:rpi = ",swrast"

do_install:append:rpi() {
    rm -rf ${D}${includedir}/KHR/khrplatform.h
}
