#!/bin/sh

num_cpus=$(grep -c processor /proc/cpuinfo)
[ "$num_cpus" != 0 ] || num_cpus=1

last_cpu=$((num_cpus - 1))

mem_by_cpu=$(awk -v cpus=$num_cpus '/MemTotal/ { print (($2 * 1024) / cpus) }' /proc/meminfo)

if [ "$1" = "--load" ] ; then
	echo zram: Trying to load kernel module.
	
	# Linux 3.2 workaround - value name changed :o.
	# modprobe -q zram zram_num_devices=$num_cpus
	
	# Linux < 3.2.
	modprobe -q zram num_devices=$num_cpus 

	echo zram: Enable in-memory compressed swap of $mem_by_cpu bytes.
	for i in $(seq 0 $last_cpu); do
		echo $mem_by_cpu > /sys/block/zram$i/disksize
		mkswap /dev/zram$i
		swapon -p 100 /dev/zram$i
	done
fi

if [ "$1" = "--unload" ] ; then
	echo zram: Disable in-memory compressed swap.
	for i in $(seq 0 $last_cpu); do
		grep -q "/dev/zram$i" /proc/swaps && swapoff /dev/zram$i
	done
	
	sleep 1
	echo zram: Unload kernel module.
	rmmod zram
fi
