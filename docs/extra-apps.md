# Extra apps

## omxplayer

omxplayer depends on libav which has a commercial license. So in order to be
able to compile omxplayer you will need to whiteflag the commercial
license in your local.conf:

    LICENSE_FLAGS_WHITELIST = "commercial"
## raspberrypi3-64

Currently, raspberrypi3-64 does not boot into graphics, this needs more investigation
and fixes, until then none of images using graphics stack would boot into UI, although
the system would be up and ready, graphics wont come up. One can use serial console or
remote login to get into system
