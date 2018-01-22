do_install_append_rpi () {
    # ensure that files and directories are created as required
    mkdir -p ${D}${sysconfdir}/modules-load.d/
    touch ${D}${sysconfdir}/modules-load.d/i2c_dev.conf
    cat >> ${D}${sysconfdir}/modules-load.d/i2c_dev.conf <<EOF
# Autoload i2c_dev
i2c_dev
EOF
}
