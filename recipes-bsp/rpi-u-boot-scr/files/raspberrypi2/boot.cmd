setenv fdtfile bcm2709-rpi-2-b.dtb
setenv bootargs 'earlyprintk console=tty0 console=ttyAMA0 root=/dev/mmcblk0p2 rootfstype=ext4 rootwait noinitrd'
mmc dev 0
fatload mmc 0:1 ${kernel_addr_r} uImage
fatload mmc 0:1 ${fdt_addr_r} ${fdtfile}
bootm ${kernel_addr_r} - ${fdt_addr_r}
