inherit image

# Add the fstypes we need
IMAGE_FSTYPES_append = " tar.bz2 rpi-sdimg"

# Ensure required utilities are present
IMAGE_DEPENDS_rpi-sdimg = "genext2fs-native e2fsprogs-native bcm2835-bootfiles bcm2835-kernel-image"

# Register this as an avalable type of image.
IMAGE_TYPES_append = " rpi-sdimg"

# Change this to match your host distro
LOSETUP ?= "/sbin/losetup"

# Since these need to go in /etc/fstab we can hardcode them
# Since the vars are weakly assigned, you can override them from your local.conf
LOOPDEV ?= "/dev/loop1"
LOOPDEV_BOOT ?= "/dev/loop2"
LOOPDEV_FS ?= "/dev/loop3"

# Default to 4GiB images
SDIMG_SIZE ?= "444" 

# FS type for rootfs
ROOTFSTYPE ?= "ext4"

BOOTPARTNAME ?= "${MACHINE}"

IMAGEDATESTAMP = "${@time.strftime('%Y.%m.%d',time.gmtime())}"

# Additional files and/or directories to be copied into the vfat partition from the IMAGE_ROOTFS.
FATPAYLOAD ?= ""

IMAGE_CMD_rpi-sdimg () {
	SDIMG=${WORKDIR}/sd.img

	# sanity check fstab entry for boot partition mounting
	if [ "x$(cat /etc/fstab | grep ${LOOPDEV_BOOT} | grep ${WORKDIR}/tmp-mnt-boot | grep user || true)" = "x" ]; then
		echo "/etc/fstab entries need to be created with the user flag for the loop devices like:"
		echo "${LOOPDEV_BOOT} ${WORKDIR}/tmp-mnt-boot vfat user 0 0"
        false
	fi

	# cleanup loops
	for loop in ${LOOPDEV} ${LOOPDEV_BOOT} ${LOOPDEV_FS} ; do
		${LOSETUP} -d $loop || true
	done

	# If an SD image is already present, reuse and reformat it
	if [ ! -e ${SDIMG} ] ; then
		dd if=/dev/zero of=${SDIMG} bs=$(echo '255 * 63 * 512' | bc) count=${SDIMG_SIZE}
	fi

	${LOSETUP} ${LOOPDEV} ${SDIMG}

	# Create partition table
	dd if=/dev/zero of=${LOOPDEV} bs=1024 count=1024
	SIZE=$(/sbin/fdisk -l ${LOOPDEV} | grep Disk | grep bytes | awk '{print $5}')
	CYLINDERS=$(echo $SIZE/255/63/512 | bc)
	{
	echo ,9,0x0C,*
	echo ,,,-
	} | /sbin/sfdisk -D -H 255 -S 63 -C ${CYLINDERS} ${LOOPDEV}

	# Prepare loop devices for boot and filesystem partitions
	BOOT_OFFSET=32256
	FS_OFFSET_SECT=$(/sbin/fdisk -l -u ${LOOPDEV} 2>&1 | grep Linux | perl -p -i -e "s/\s+/ /"|cut -d " " -f 2)
	FS_OFFSET=$(echo "$FS_OFFSET_SECT * 512" | bc)
	FS_SIZE_BLOCKS=$(/sbin/fdisk -l -u ${LOOPDEV} 2>&1 | grep Linux | perl -p -i -e "s/\s+/ /g" \ 
	|cut -d " " -f 4 | cut -d "+" -f 1)
 
	LOOPDEV_BLOCKS=$(/sbin/fdisk -l -u ${LOOPDEV} 2>&1 | grep FAT | perl -p -i -e "s/\s+/ /g"|cut -d " " -f 5)
	LOOPDEV_BYTES=$(echo "$LOOPDEV_BLOCKS * 1024" | bc)

	${LOSETUP} -d ${LOOPDEV}

	${LOSETUP} ${LOOPDEV_BOOT} ${SDIMG} -o ${BOOT_OFFSET} 

	/sbin/mkfs.vfat ${LOOPDEV_BOOT} -n ${BOOTPARTNAME} $LOOPDEV_BLOCKS

	# Prepare boot partion. First mount the boot partition, and copy the bootloader and supporting files.

	mkdir -p ${WORKDIR}/tmp-mnt-boot
	mount $LOOPDEV_BOOT ${WORKDIR}/tmp-mnt-boot

	echo "Copying bootloader and prepended kernel.img into the boot partition"
	cp -v ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/* ${WORKDIR}/tmp-mnt-boot || true

	if [ -n ${FATPAYLOAD} ] ; then
		echo "Copying payload into VFAT"
		for entry in ${FATPAYLOAD} ; do
				# add the || true to stop aborting on vfat issues like not supporting .~lock files
				cp -av ${IMAGE_ROOTFS}$entry ${WORKDIR}/tmp-mnt-boot || true
		done
	fi

	echo "${IMAGE_NAME}-${IMAGEDATESTAMP}" > ${IMAGE_ROOTFS}/etc/image-version-info
	
	cp -v ${IMAGE_ROOTFS}/etc/image-version-info ${WORKDIR}/tmp-mnt-boot || true

	# Cleanup VFAT mount
	echo "Cleaning up VFAT mount"
	umount ${WORKDIR}/tmp-mnt-boot
	${LOSETUP} -d ${LOOPDEV_BOOT} || true

	# Prepare rootfs parition
	echo "Creating rootfs loopback"
	${LOSETUP} ${LOOPDEV_FS} ${SDIMG} -o ${FS_OFFSET}

	FS_NUM_INODES=$(echo $FS_SIZE_BLOCKS / 4 | bc)

	case "${ROOTFSTYPE}" in
		ext3)
				genext2fs -z -N $FS_NUM_INODES -b $FS_SIZE_BLOCKS -d ${IMAGE_ROOTFS} ${LOOPDEV_FS}
				tune2fs -L ${IMAGE_NAME} -j ${LOOPDEV_FS}
				;;
		ext4)
				genext2fs -z -N $FS_NUM_INODES -b $FS_SIZE_BLOCKS -d ${IMAGE_ROOTFS} ${LOOPDEV_FS}
				tune2fs -L ${IMAGE_NAME} -j -O extents,uninit_bg,dir_index ${LOOPDEV_FS}
				;;
		*)
				echo "Please set ROOTFSTYPE to something supported"
				exit 1
				;;
	esac

	${LOSETUP} -d ${LOOPDEV_FS} || true

	gzip -c ${WORKDIR}/sd.img > ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}-${IMAGEDATESTAMP}.img.gz
	rm -f ${WORKDIR}/sd.img
}
