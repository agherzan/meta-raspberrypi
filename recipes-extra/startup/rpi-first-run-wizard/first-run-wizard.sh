#!/bin/sh

# Initially based on the scripts by JohnX/Mer Project - http://wiki.maemo.org/Mer/
# Reworked for the OpenPandora - John Willis/Michael Mrozek
# Quickly 'hacked' for the Raspberry Pi to provide a simple 1st boot wizard.

# You can start the wizard from the shell using 'xinit ./first-boot-init.sh'

export LANG=en_GB.UTF-8
export GTK2_RC_FILES=/usr/share/themes/Xfce/gtk-2.0/gtkrc

# Ensure there is a wheel group for sudoers to be put into.
# TODO: Do this somewhere better.
groupadd wheel

# Default error message (should a user hit cancel, validation fail etc.).
ERROR_WINDOW='zenity --title="Error" --error --text="Sorry! Please try again." --timeout 6'

RESET_ROOT="yes"

DISPLAY=:0 xset s off

# Greet the user.

if zenity --question --title="Pandoras Box has been opened." --text="Welcome!\n\nPandora's Box has been opened.\n\nThis wizard will help you setting up your new OpenPandora handheld before the first use.\n\nYou will be asked a few simple questions to personalise and configure your device.\n\nDo you want to set up your unit now or shut the unit down and do it later?" --ok-label="Start now" --cancel-label="Shutdown" ; then
# ----

# Reset ROOT's password to something random 

# (I know the image build sets the password to something pusdo-random)
# (ok, urandom is not 100% secure but it's good enough for our needs)

if [ $RESET_ROOT == "yes" ]; then
	rootpwd=$(cat /dev/urandom|tr -dc "a-zA-Z0-9-_\$\?"|fold -w 30|head -n 1)
passwd "root" <<EOF
$rootpwd
$rootpwd
EOF
	rootpwd=""
fi

# ----

# Setup swap partition if the user has placed an SD with a swap partition on it.

swap_part=$(sfdisk -l /dev/mmcblk? | grep swap | cut -d" " -f1)
if [ x$swap_part != x ] ; then
	use_swap=$(zenity --title="Enable swap?" --text "Swap partition found on SD card. Would you like to use it?\n\nWarning: This SD must remain in the system to use the swap." --list --radiolist --column " " --column "Answer" TRUE "Use swap on $swap_part" FALSE "Do not use swap")
	if [ "$use_swap" = "Use swap on $swap_part" ] ; then
		swapon $swap_part
       		echo "$swap_part none swap sw 0 0" >> /etc/fstab
	fi
fi

# ----

# Setup the full name and username.

while ! name=$(zenity --title="Please enter your full name" --entry --text "Please enter your full name.") || [ "x$name" = "x" ] ; do
	zenity --title="Error" --error --text="Please try again." --timeout 6
done

username_guess=$(echo "$name" | cut -d" " -f1 | tr A-Z a-z)

while ! username=$(zenity --title="Enter your username" --entry --text "Please choose a short username.\n\nIt should be all lowercase and contain only letters and numbers." --entry-text "$username_guess") || [ "x$username" = "x" ] ; do
	zenity --title="Error" --error --text="Please try again." --timeout 6
done

while ! useradd -c "$name,,," -G adm,audio,video,netdev,wheel,plugdev,users "$username" ; do
	username=$(zenity --title="Please check username" --entry --text "Please ensure that your username consists of only\nletters and numbers and is not already in use on the system." --entry-text "$username")
done

# ----

# Setup the users password.

password=""
while [ x$password = x ] ; do
	password1=$(zenity --title=Password --entry --text="Please choose a new password." --hide-text)
	password2=$(zenity --title=Confirm --entry --text="Confirm your new password." --hide-text)
	if [ $password1 != $password2 ] ; then 
		zenity --title="Error" --error --text="The passwords do not match.\n\nPlease try again." --timeout 6
	else 
		if [ x$password1 = x ] ; then
			zenity --title="Error" --error --text="Password cannot be blank!\n\nPlease try again." --timeout 6
		else
			password=$password1
		fi
	fi
done

passwd "$username" <<EOF
$password
$password
EOF

# ----

# Pick a name for the OpenPandora.

while ! hostname=$(zenity --title="Name your Pandora" --entry --text "Please choose a name for your OpenPandora.\n\nIt should only contain letters, numbers and dashes, no spaces." --entry-text "$username-openpandora") || [ "x$hostname" = "x" ]; do 
	zenity --title="Error" --error --text="Please try again."
done


echo $hostname > /etc/hostname
hostname =$(sed 's/ /_/g' /etc/hostname)
echo $hostname > /etc/hostname
echo "127.0.0.1 localhost.localdomain localhost $hostname" > /etc/hosts
hostname -F /etc/hostname

# Set the correct user for Autologin and enable / disable it.

if zenity --question --title="Autologin" --text="Do you wish to automatically login at startup?\n\nSecurity warning: This skips the password check on startup" --ok-label="Yes" --cancel-label="No"; then      	
	# echo "PREFERED_USER=$username" > /etc/default/autologin
	sed -i "s/.*default_user.*/default_user $username/g" /etc/slim.conf
	sed -i 's/.*auto_login.*/auto_login yes/g' /etc/slim.conf
else
	if zenity --question --title="User" --text="Do you wish to have your username automatically populated in the login screen?\n\nNote: This is ideal if you're the only user of the OpenPandora but wish to disable autologin and use a password." --ok-label="Yes" --cancel-label="No"; then 
		sed -i "s/.*default_user.*/default_user $username/g" /etc/slim.conf
		sed -i 's/.*auto_login.*/auto_login no/g' /etc/slim.conf
	else
		sed -i "s/.*default_user.*/default_user/g" /etc/slim.conf
		sed -i 's/.*auto_login.*/auto_login no/g' /etc/slim.conf
	fi
fi

# ----

# Select the default interface and setup SLiM to pass that as a sesion to ~./.xinitrc

selection=""
while [ x$selection = x ]; do
selection=$(cat /etc/pandora/conf/gui.conf | awk -F\; '{print $1 "\n" $2 }' | zenity --width=500 --height=300 --title="Select the Default GUI" --list --column "Name" --column "Description" --text "Please select the Default GUI" )
if [ x$selection = x ]; then
  zenity --title="Error" --error --text="Please select a GUI." --timeout=6
fi
done

echo $selection

gui=$(grep $selection /etc/pandora/conf/gui.conf | awk -F\; '{print $3}')
stopcommand=$(grep $selection /etc/pandora/conf/gui.conf | awk -F\; '{print $4}')

echo $gui

if [ $gui ]; then 
  sed -i "s/.*DEFAULT_SESSION=.*/DEFAULT_SESSION=$gui/g" /home/$username/.xinitrc
  echo $selection selected as default interface
  zenity --info --title="Selected session" --text "You selected $selection as default setting. You can always change your default GUI later." --timeout 6
else
  sed -i 's/.*DEFAULT_SESSION=.*/DEFAULT_SESSION=startxfce4/g' /home/$username/.xinitrc
fi

# ----

# Set the timezone and date/time

while ! area=$(zenity --list --title "Select your time zone" --text="Please select your area" --column="Select your area" --print-column=1 "Africa" "America" "Asia" "Australia" "Europe" "Pacific" --width=500 --height=260) || [ "x$area" = "x" ] ; do
	zenity --title="Error" --error --text="Please select your area." --timeout=6
done

while ! timezone=$(ls -1 /usr/share/zoneinfo/$area | zenity ---width=500 --height=200 --title="Select your closest location" --list --column "Closest Location" --text "Please select the location closest to you") || [ "x$timezone" = "x" ] ; do
	zenity --title="Error" --error --text="Please select your location." --timeout=6
done

echo $timezone
rm /etc/localtime && ln -s /usr/share/zoneinfo/$area/$timezone /etc/localtime

# Make sure we clean up any leading zeros in the day (as Zenity freaks out)
date_d=`date +%d | sed 's/^0//'`
date_m=`date +%m | sed 's/^0//'`
date_y=`date +%Y`

while ! date=$(zenity --calendar --text="Please select the current date" --title "Please select the current date" --day=$date_d --month=$date_m --year=$date_y --date-format="%Y%m%d" --width=500) || [ "x$date" = "x" ] ; do
        zenity --title="Error" --error --text="Please select the date." --timeout 6
done

echo $date

time_h=`date +%H`
time_m=`date +%M`

while ! time=$(zenity --title="Enter actual time" --entry --text "Please enter the time in 24hour format (HH:MM):" --entry-text "$time_h:$time_m") || [ "x$time" = "x" ] ; do
        zenity --title="Error" --error --text="Please input the time." --timeout 6
done

while ! date -d $time ; do
	time=$(zenity --title="Enter actual time" --entry --text "Please enter the time in 24hour format (HH:MM):" --entry-text "$time_h:$time_m")
done
 
date +%Y%m%d -s $date
date +%H:%M -s $time

# ----

# Finsh up and boot the system.

zenity --info --title="Finished" --text "This concludes the First Boot Wizard.\n\nYour chosen interface will start in a few seconds\n\nThankyou for buying the OpenPandora. Enjoy using the device!" --timeout 6

# ----

# Write the control file so this script is not run on next boot 
# (hackish I know but I want the flexability to drop a new script in later esp. in the early firmwares).

touch /etc/rpi/first-boot
# Make the control file writeable by all to allow the user to delete to rerun the wizard on next boot.
chmod 0666 /etc/rpi/first-boot

# ----
else
poweroff
fi
