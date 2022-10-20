# SPDX-FileCopyrightText: meta-raspberrypi contributors
#
# SPDX-License-Identifier: MIT

EXTRA_OECONF:append:raspberrypi = " --disable-asm"
EXTRA_OECONF:append:raspberrypi0-wifi = " --disable-asm"
