# Extra apps

## omxplayer

omxplayer depends on libav which has a commercial license. So in order to be
able to compile omxplayer you will need to whiteflag the commercial
licenseadding to you local.conf:
`LICENSE_FLAGS_WHITELIST = "commercial"`
