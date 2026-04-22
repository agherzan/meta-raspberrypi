require optee-os.inc

DEPENDS += "dtc-native"

SRCREV = "8f645256efc0dc66bd5c118778b0b50c44469ae1"

COMPATIBLE_MACHINE = "^rpi$"

EXTRA_OEMAKE += "\
                CFG_DEBUG_INFO=y \
                CFG_TEE_CORE_DEBUG=y \
                CFG_TEE_CORE_LOG_LEVEL=4 \
                CFG_TEE_TA_LOG_LEVEL=3 \
                CFG_DT=y \
            "

FILES:${PN} = " \
                /lib/firmware/* \
                /lib/optee_armtz/* \
                "