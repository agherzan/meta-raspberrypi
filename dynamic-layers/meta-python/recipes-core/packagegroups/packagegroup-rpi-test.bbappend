# SPDX-FileCopyrightText: meta-raspberrypi contributors
#
# SPDX-License-Identifier: MIT

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

RDEPENDS:${PN} += "python3-sense-hat"

