# This provider of tensorflow-lite only builds on 64-bit systems
COMPATIBLE_MACHINE:rpi = "^(aarch64)$"

# https://github.com/nnstreamer/meta-neural-network/pull/85
do_install:append () {
	ln -sf libtensorflow2-lite.a ${D}${libdir}/libtensorflow-lite.a
	ln -sf tensorflow2-lite.pc ${D}${libdir}/pkgconfig/tensorflow-lite.pc
}
