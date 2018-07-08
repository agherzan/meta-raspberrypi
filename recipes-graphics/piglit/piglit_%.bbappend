# mesa-demos need libgles1 and userland driver does not have it so remove it from piglit rdeps
RDEPENDS_${PN}_remove_rpi = "${@bb.utils.contains('MACHINE_FEATURES', 'vc4graphics', '', 'mesa-demos', d)}"
