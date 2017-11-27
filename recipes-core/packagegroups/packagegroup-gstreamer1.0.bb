DESCRIPTION = "RaspberryPi GStreamer Packagegroup"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit packagegroup

COMPATIBLE_MACHINE = "^rpi$"

OMXPLAYER_rpi = "omxplayer"
OMXPLAYER_rpi_aarch64 = ""

RDEPENDS_${PN} = "\
  glib-2.0 \
  gstreamer1.0 \
  gstreamer1.0-omx \
  gstreamer1.0-libav \
  gstreamer1.0-plugins-base \
  gstreamer1.0-plugins-good \
  gstreamer1.0-plugins-bad \
  gstreamer1.0-plugins-ugly \
  gstreamer1.0-rtsp-server \
"

