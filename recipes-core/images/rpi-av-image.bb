# Base this image on rpi-basic-image
include rpi-basic-image.bb

IMAGE_FEATURES += " package-management"

IMAGE_LINGUAS = "en-us"

COMPATIBLE_MACHINE = "^rpi$"

OMXPLAYER_rpi = "omxplayer"
OMXPLAYER_rpi_aarch64 = ""

IMAGE_INSTALL_append = " \
  packagegroup-core-full-cmdline \
  packagegroup-gstreamer1.0 \
  ${OMXPLAYER} \
  sintel-trailer-480p \
  sintel-trailer-720p \
  sintel-trailer-1080p \
"

# toolbox
IMAGE_INSTALL_append += " \
  python-modules \
  python3-modules \
  nano \
  cpufrequtils \
  strace \
  perf \
  tree \
  htop \
  tcpdump \
  i2c-tools \
  can-utils \
  usbutils \
  spitools \
  iperf3 \
  stress \
  cmake \
  automake \
  autoconf \
  git \
  subversion \
  wget \
  e2fsprogs \
  tzdata \
  watchdog \
  start-stop-daemon \
  logrotate \
"

IMAGE_INSTALL_append += " v4l-utils"

#IMAGE_INSTALL_append += " \
#  opencv \
#  ffmpeg \
#"

# web server stuff
IMAGE_INSTALL_append += " \
  lighttpd \
  lighttpd-module-fastcgi \
  php \
  php-cli \
"

# for WiFi client & access point
IMAGE_INSTALL_append += " \
  linux-firmware-bcm43430 \
  wireless-tools \
  wpa-supplicant \
  hostapd \
  dnsmasq \
  iptables \
  ntp \
  crda \
  iw \
"

# blueetooth stuff
IMAGE_INSTALL_append += " \
  bluez5 \
  bluez5-noinst-tools \
"

# lttng tracing tools, see http://lttng.org/docs/
IMAGE_INSTALL_append += " \
  lttng-tools \
  lttng-modules \
  lttng-ust \
  babeltrace \
"

set_date_and_time() {
  ln -sf /usr/share/zoneinfo/CET ${IMAGE_ROOTFS}/etc/localtime
  echo "server 3.pool.ntp.org iburst" >> ${IMAGE_ROOTFS}/etc/ntp.conf
  echo "server 2.pool.ntp.org iburst" >> ${IMAGE_ROOTFS}/etc/ntp.conf
  echo "server 1.pool.ntp.org iburst" >> ${IMAGE_ROOTFS}/etc/ntp.conf
  echo "server 0.pool.ntp.org iburst" >> ${IMAGE_ROOTFS}/etc/ntp.conf
}

ROOTFS_POSTPROCESS_COMMAND += " \
  set_date_and_time ; \
"

