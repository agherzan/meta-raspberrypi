FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

# disable libjitterentropy because of sustained cpu use after boot
# instead we depend on /dev/hwrng via config
PACKAGECONFIG = ""
