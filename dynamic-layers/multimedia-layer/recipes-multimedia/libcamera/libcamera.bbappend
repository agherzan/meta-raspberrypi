PACKAGECONFIG[raspberrypi] = "-Dpipelines=raspberrypi -Dipas=raspberrypi -Dcpp_args=-Wno-unaligned-access"
# SPDX-FileCopyrightText: meta-raspberrypi contributors
#
# SPDX-License-Identifier: MIT
PACKAGECONFIG:append:rpi = " raspberrypi"
