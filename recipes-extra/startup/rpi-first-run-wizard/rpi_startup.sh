#!/bin/sh

OP_CHECKFILE='/etc/rpi/first-boot'
OP_FIRSTRUN='xinit /usr/rpi/scripts/first-run-wizard.sh'

[ -f $OP_CHECKFILE ] && echo -e "\nOP_STARTUP: $OP_CHECKFILE exists, not first boot." || $OP_FIRSTRUN
