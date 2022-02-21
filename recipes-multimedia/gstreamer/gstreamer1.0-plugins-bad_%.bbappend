PACKAGECONFIG:append:rpi = " hls \
                   ${@bb.utils.contains('LICENSE_FLAGS_WHITELIST', 'commercial', 'gpl faad', '', d)}"
