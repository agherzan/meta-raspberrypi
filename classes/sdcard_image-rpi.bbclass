#
# Create an image that can by written onto a SD card using dd.
#
# The disk layout used is:
#
#    0  - 1M                  - reserved for other data
#    1M - BOOT_SPACE          - bootloader and kernel
#    BOOT_SPACE - SDIMG_SIZE  - rootfs
#

# Set kernel and boot loader
IMAGE_BOOTLOADER ?= "bcm2835-bootfiles"

# Default to 1.4GiB images
SDIMG_SIZE ?= "4000"

# Boot partition volume id
BOOTDD_VOLUME_ID ?= "${MACHINE}"

# Addional space for boot partition
BOOT_SPACE ?= "20MiB"

# Use an ext3 by default as rootfs
SDIMG_ROOTFS_TYPE ?= "ext3"
SDIMG_ROOTFS = "${IMAGE_NAME}.rootfs.${SDIMG_ROOTFS_TYPE}"

# Set GPU firmware image to be used
# arm128 : 128M ARM, 128M GPU split
# arm192 : 192M ARM, 64M GPU split
# arm224 : 224M ARM, 32M GPU split
RPI_GPU_FIRMWARE ?= "arm192"

IMAGE_DEPENDS_rpi-sdimg = " \
			parted-native \
			mtools-native \
			dosfstools-native \
			virtual/kernel \
			${IMAGE_BOOTLOADER} \
			"

# SD card image name
SDIMG = "${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.rpi-sdimg"

# Additional files and/or directories to be copied into the vfat partition from the IMAGE_ROOTFS.
FATPAYLOAD ?= ""

IMAGEDATESTAMP = "${@time.strftime('%Y.%m.%d',time.gmtime())}"

IMAGE_CMD_rpi-sdimg () {
	# Initialize sdcard image file
	dd if=/dev/zero of=${SDIMG} bs=1 count=0 seek=$(expr 1000 \* 1000 \* ${SDIMG_SIZE})

	# Create partition table
	parted -s ${SDIMG} mklabel msdos
	# Create boot partition and mark it as bootable
	parted -s ${SDIMG} mkpart primary fat32 1MiB ${BOOT_SPACE}
	parted -s ${SDIMG} set 1 boot on
	# Create rootfs partition
	parted -s ${SDIMG} mkpart primary ext2 ${BOOT_SPACE} 100%
	parted ${SDIMG} print

	# Create a vfat image with boot files
	BOOT_BLOCKS=$(LC_ALL=C parted -s ${SDIMG} unit b print | awk '/ 1 / { print substr($4, 1, length($4 -1)) / 512 /2 }')
	mkfs.vfat -n "${BOOTDD_VOLUME_ID}" -S 512 -C ${WORKDIR}/boot.img $BOOT_BLOCKS
	case "${RPI_GPU_FIRMWARE}" in
		"arm128" | "arm192" | "arm224")
			mcopy -i ${WORKDIR}/boot.img -s ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/${RPI_GPU_FIRMWARE}_start.elf ::start.elf
			;;
		*)
			bberror "RPI_GPU_FIRMWARE is undefined or value not recongnised. Possible values: arm128, arm192 or arm224."
			exit 1
			;;
	esac

	# To do
	# Copy here a cmdline.txt file generated taking into consideration the partition type
	# of the rootfs
	mcopy -i ${WORKDIR}/boot.img -s ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/bootcode.bin ::
	mcopy -i ${WORKDIR}/boot.img -s ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/loader.bin ::
	mcopy -i ${WORKDIR}/boot.img -s ${DEPLOY_DIR_IMAGE}/${KERNEL_IMAGETYPE}-${MACHINE}.bin ::kernel.img

	if [ -n ${FATPAYLOAD} ] ; then
		echo "Copying payload into VFAT"
		for entry in ${FATPAYLOAD} ; do
				# add the || true to stop aborting on vfat issues like not supporting .~lock files
				mcopy -i ${WORKDIR}/boot.img -s -v ${IMAGE_ROOTFS}$entry :: || true
		done
	fi

	# Add stamp file
	echo "${IMAGE_NAME}-${IMAGEDATESTAMP}" > ${WORKDIR}/image-version-info
	mcopy -i ${WORKDIR}/boot.img -v ${WORKDIR}//image-version-info ::

	# Burn Partitions
	dd if=${WORKDIR}/boot.img of=${SDIMG} conv=notrunc seek=1 bs=1M && sync && sync
	dd if=${SDIMG_ROOTFS} of=${SDIMG} conv=notrunc seek=1 bs=${BOOT_SPACE} && sync && sync
}
