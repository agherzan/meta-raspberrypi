DESCRIPTION = "Commented config.txt file for the Raspberry Pi. \
               The Raspberry Pi config.txt file is read by the GPU before \
               the ARM core is initialised. It can be used to set various \
               system configuration parameters."
HOMEPAGE = "https://www.raspberrypi.com/documentation/computers/config_txt.html"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

COMPATIBLE_MACHINE = "^rpi$"

SRC_URI = "file://config.txt"

S = "${WORKDIR}"
B = "${WORKDIR}/build"

INHIBIT_DEFAULT_DEPS = "1"

PITFT="${@bb.utils.contains("MACHINE_FEATURES", "pitft", "1", "0", d)}"
PITFT22="${@bb.utils.contains("MACHINE_FEATURES", "pitft22", "1", "0", d)}"
PITFT28r="${@bb.utils.contains("MACHINE_FEATURES", "pitft28r", "1", "0", d)}"
PITFT28c="${@bb.utils.contains("MACHINE_FEATURES", "pitft28c", "1", "0", d)}"
PITFT35r="${@bb.utils.contains("MACHINE_FEATURES", "pitft35r", "1", "0", d)}"

VC4GRAPHICS="${@bb.utils.contains("MACHINE_FEATURES", "vc4graphics", "1", "0", d)}"
VC4DTBO ?= "vc4-kms-v3d"
GPIO_IR ?= "18"
GPIO_IR_TX ?= "17"

CAN_OSCILLATOR ?= "16000000"

ENABLE_UART ??= ""

WM8960="${@bb.utils.contains("MACHINE_FEATURES", "wm8960", "1", "0", d)}"

GPIO_SHUTDOWN_PIN ??= ""

inherit deploy nopackages

# Camera configuration (see documentation for details)
RASPBERRYPI_CAMERA ??= "auto"

# TODO Drop the weak V2/V3 assignments once the support for
# deprecated RASPBERRYPI_CAMERA_V2/V3 = "1" flags is dropped.
RASPBERRYPI_CAMERA_V1 = "ov5647"
RASPBERRYPI_CAMERA_V2 ?= "imx219"
RASPBERRYPI_CAMERA_HQ = "imx477"
RASPBERRYPI_CAMERA_GS = "imx296"
RASPBERRYPI_CAMERA_V3 ?= "imx708"

do_compile() {
    CONFIG=${B}/config.txt

    cp ${S}/config.txt $CONFIG

    if [ -n "${KEY_DECODE_MPG2}" ]; then
        echo "decode_MPG2=${KEY_DECODE_MPG2}" >> $CONFIG
    fi
    if [ -n "${KEY_DECODE_WVC1}" ]; then
        echo "decode_WVC1=${KEY_DECODE_WVC1}" >> $CONFIG
    fi
    if [ -n "${DISABLE_OVERSCAN}" ]; then
        echo "disable_overscan=${DISABLE_OVERSCAN}" >> $CONFIG
    fi
    if [ "${DISABLE_SPLASH}" = "1" ]; then
        echo "disable_splash=${DISABLE_SPLASH}" >> $CONFIG
    fi

    # Set overclocking options
    if [ -n "${ARM_FREQ}" ]; then
        echo "arm_freq=${ARM_FREQ}" >> $CONFIG
    fi
    if [ -n "${GPU_FREQ}" ]; then
        echo "gpu_freq=${GPU_FREQ}" >> $CONFIG
    fi
    if [ -n "${CORE_FREQ}" ]; then
        echo "core_freq=${CORE_FREQ}" >> $CONFIG
    fi
    if [ -n "${SDRAM_FREQ}" ]; then
        echo "sdram_freq=${SDRAM_FREQ}" >> $CONFIG
    fi
    if [ -n "${OVER_VOLTAGE}" ]; then
        echo "over_voltage=${OVER_VOLTAGE}" >> $CONFIG
    fi

    # GPU memory
    if [ -n "${GPU_MEM}" ]; then
        echo "gpu_mem=${GPU_MEM}" >> $CONFIG
    fi
    if [ -n "${GPU_MEM_256}" ]; then
        echo "gpu_mem_256=${GPU_MEM_256}" >> $CONFIG
    fi
    if [ -n "${GPU_MEM_512}" ]; then
        echo "gpu_mem_512=${GPU_MEM_512}" >> $CONFIG
    fi
    if [ -n "${GPU_MEM_1024}" ]; then
        echo "gpu_mem_1024=${GPU_MEM_1024}" >> $CONFIG
    fi

    # Set boot delay
    if [ -n "${BOOT_DELAY}" ]; then
        echo "boot_delay=${BOOT_DELAY}" >> $CONFIG
    fi
    if [ -n "${BOOT_DELAY_MS}" ]; then
        echo "boot_delay_ms=${BOOT_DELAY_MS}" >> $CONFIG
    fi

    # Set HDMI and composite video options
    if [ -n "${HDMI_FORCE_HOTPLUG}" ]; then
        echo "hdmi_force_hotplug=${HDMI_FORCE_HOTPLUG}" >> $CONFIG
    fi
    if [ -n "${HDMI_DRIVE}" ]; then
        echo "hdmi_drive=${HDMI_DRIVE}" >> $CONFIG
    fi
    if [ -n "${HDMI_GROUP}" ]; then
        echo "hdmi_group=${HDMI_GROUP}" >> $CONFIG
    fi
    if [ -n "${HDMI_MODE}" ]; then
        echo "hdmi_mode=${HDMI_MODE}" >> $CONFIG
    fi
    if [ -n "${HDMI_CVT}" ]; then
        echo 'hdmi_cvt=${HDMI_CVT}' >> $CONFIG
    fi
    if [ -n "${CONFIG_HDMI_BOOST}" ]; then
        echo "config_hdmi_boost=${CONFIG_HDMI_BOOST}" >> $CONFIG
    fi
    if [ -n "${SDTV_MODE}" ]; then
        echo "sdtv_mode=${SDTV_MODE}" >> $CONFIG
    fi
    if [ -n "${SDTV_ASPECT}" ]; then
        echo "sdtv_aspect=${SDTV_ASPECT}" >> $CONFIG
    fi
    if [ -n "${DISPLAY_ROTATE}" ]; then
        echo "display_rotate=${DISPLAY_ROTATE}" >> $CONFIG
    fi

    if [ "${VIDEO_CAMERA}" = "1" ]; then
        bbwarn "The VIDEO_CAMERA variable is deprecated, see RASPBERRYPI_CAMERA instead."
        RASPBERRYPI_CAMERA="auto"
    fi

    if [ "${RASPBERRYPI_CAMERA_V2}" = "1" ]; then
        bbwarn "Setting RASPBERRYPI_CAMERA_V2 = \"1\" is deprecated, set RASPBERRYPI_CAMERA = \"imx219\" to enable support for V2 camera."
        RASPBERRYPI_CAMERA="imx219"
    fi

    if [ "${RASPBERRYPI_CAMERA_V3}" = "1" ]; then
        bbwarn "Setting RASPBERRYPI_CAMERA_V3 = \"1\" is deprecated, set RASPBERRYPI_CAMERA = \"imx708\" to enable support for Camera Module 3."
        RASPBERRYPI_CAMERA="imx708"
    fi

    if [ -n "${RASPBERRYPI_CAMERA}" ]; then
        #   It has been observed that Raspberry Pi 4B 4GB may fail to enable the
        # camera if "start_x=1" is at the end of the file. Therefore,
        # "start_x=1" has been set to replace the original occurrence in
        # config.txt, which is at the middle of the file.
        #   The exact underlying cause is unknown. There are similar issues
        # reported in the raspberrypi/firware repo and the conclusion reached
        # was that there could be a file size limitation affecting certain
        # variables. It was commented that this limitation could be 4k but
        # not proved.
        echo "start_x=1" >> $CONFIG

        case "${RASPBERRYPI_CAMERA}" in
            auto)
                echo "camera_auto_detect=1" >> $CONFIG
                ;;
            imx290)
                echo "dtoverlay=imx290,clock-frequency=74250000" >> $CONFIG
                ;;
            imx327)
                echo "dtoverlay=imx290,clock-frequency=37125000" >> $CONFIG
                ;;
            *)
                echo "dtoverlay=${RASPBERRYPI_CAMERA}" >> $CONFIG
                ;;
        esac
    fi

    # Offline compositing support
    if [ "${DISPMANX_OFFLINE}" = "1" ]; then
        echo "# Enable offline compositing" >>$CONFIG
        echo "dispmanx_offline=1" >>$CONFIG
    fi

    # SPI bus support
    if [ "${ENABLE_SPI_BUS}" = "1" ] || [ "${PITFT}" = "1" ]; then
        echo "# Enable SPI bus" >>$CONFIG
        echo "dtparam=spi=on" >>$CONFIG
    fi

    # I2C support
    if [ "${ENABLE_I2C}" = "1" ] || [ "${PITFT}" = "1" ]; then
        echo "# Enable I2C" >>$CONFIG
        echo "dtparam=i2c1=on" >>$CONFIG
        echo "dtparam=i2c_arm=on" >>$CONFIG
    fi

    # PiTFT22 display support
    if [ "${PITFT22}" = "1" ]; then
        echo "# Enable PITFT22 display" >>$CONFIG
        echo "dtoverlay=pitft22,rotate=270,speed=32000000,txbuflen=32768" >>$CONFIG
    fi
    if [ "${PITFT28r}" = "1" ]; then
        echo "# Enable PITFT28r display" >>$CONFIG
        echo "dtoverlay=pitft28-resistive,rotate=90,speed=32000000,txbuflen=32768" >>$CONFIG
    fi
    if [ "${PITFT28c}" = "1" ]; then
        echo "# Enable PITFT28c display" >>$CONFIG
        echo "dtoverlay=pitft28-capacitive,rotate=90,speed=32000000,txbuflen=32768" >>$CONFIG
        echo "dtoverlay=pitft28-capacitive,touch-swapxy,touch-invx" >>$CONFIG
    fi
    if [ "${PITFT35r}" = "1" ]; then
        echo "# Enable PITFT35r display" >>$CONFIG
        echo "dtoverlay=pitft35-resistive,rotate=90,speed=42000000,fps=20" >>$CONFIG
    fi

    # UART support
    if [ "${ENABLE_UART}" = "1" ] || [ "${ENABLE_UART}" = "0" ]; then
        echo "# Enable UART" >>$CONFIG
        echo "enable_uart=${ENABLE_UART}" >>$CONFIG
    elif [ -n "${ENABLE_UART}" ]; then
        bbfatal "Invalid value for ENABLE_UART [${ENABLE_UART}]. The value for ENABLE_UART can be 0 or 1."
    fi

    # U-Boot requires "enable_uart=1" for various boards to operate correctly
    # cf https://source.denx.de/u-boot/u-boot/-/blob/v2023.04/arch/arm/mach-bcm283x/Kconfig?ref_type=tags#L65
    if [ "${RPI_USE_U_BOOT}" = "1" ] && [ "${ENABLE_UART}" != "1" ]; then
        case "${UBOOT_MACHINE}" in
            rpi_0_w_defconfig|rpi_3_32b_config|rpi_4_32b_config|rpi_arm64_config)
                if [ "${ENABLE_UART}" = "0" ]; then
                    bbfatal "Invalid configuration: RPI_USE_U_BOOT requires to enable the UART in config.txt for ${MACHINE}"
                fi
                echo "# U-Boot requires UART" >>$CONFIG
                echo "enable_uart=1" >>$CONFIG
                ;;
        esac
    fi

    # Infrared support
    if [ "${ENABLE_IR}" = "1" ]; then
        echo "# Enable infrared" >>$CONFIG
        echo "dtoverlay=gpio-ir,gpio_pin=${GPIO_IR}" >>$CONFIG
        echo "dtoverlay=gpio-ir-tx,gpio_pin=${GPIO_IR_TX}" >>$CONFIG
    fi

    # VC4 Graphics support
    if [ "${VC4GRAPHICS}" = "1" ]; then
        echo "# Enable VC4 Graphics" >> $CONFIG
        echo "dtoverlay=${VC4DTBO}" >> $CONFIG
    fi

    # Waveshare "C" 1024x600 7" Rev2.1 IPS capacitive touch (http://www.waveshare.com/7inch-HDMI-LCD-C.htm)
    if [ "${WAVESHARE_1024X600_C_2_1}" = "1" ]; then
        echo "# Waveshare \"C\" 1024x600 7\" Rev2.1 IPS capacitive touch screen" >> $CONFIG
        echo "max_usb_current=1" >> $CONFIG
        echo "hdmi_group=2" >> $CONFIG
        echo "hdmi_mode=87" >> $CONFIG
        echo "hdmi_cvt 1024 600 60 6 0 0 0" >> $CONFIG
        echo "hdmi_drive=1" >> $CONFIG
    fi

    # DWC2 USB peripheral support
    if ([ "${ENABLE_DWC2_PERIPHERAL}" = "1" ] && [ "${ENABLE_DWC2_OTG}" != "1" ]); then
        echo "# Enable USB peripheral mode" >> $CONFIG
        echo "dtoverlay=dwc2,dr_mode=peripheral" >> $CONFIG
    fi

    # DWC2 USB host mode support
    if [ "${ENABLE_DWC2_HOST}" = "1" ]; then
        echo "# Enable USB host mode" >> $CONFIG
        echo "dtoverlay=dwc2,dr_mode=host" >> $CONFIG
    fi
    
    # DWC2 USB OTG support
    if ([ "${ENABLE_DWC2_OTG}" = "1" ] && [ "${ENABLE_DWC2_PERIPHERAL}" != "1" ]); then
        echo "# Enable USB OTG mode" >> $CONFIG
        echo "dtoverlay=dwc2,dr_mode=otg" >> $CONFIG
    fi

    # AT86RF23X support
    if [ "${ENABLE_AT86RF}" = "1" ]; then
        echo "# Enable AT86RF23X" >>$CONFIG
        echo "dtoverlay=at86rf233,speed=3000000" >>$CONFIG
    fi

    # ENABLE DUAL CAN
    if [ "${ENABLE_DUAL_CAN}" = "1" ]; then
        echo "# Enable DUAL CAN" >>$CONFIG
        echo "dtoverlay=mcp2515-can0,oscillator=${CAN_OSCILLATOR},interrupt=25" >>$CONFIG
        echo "dtoverlay=mcp2515-can1,oscillator=${CAN_OSCILLATOR},interrupt=24" >>$CONFIG
    # ENABLE CAN
    elif [ "${ENABLE_CAN}" = "1" ]; then
        echo "# Enable CAN" >>$CONFIG
        echo "dtoverlay=mcp2515-can0,oscillator=${CAN_OSCILLATOR},interrupt=25" >>$CONFIG
    fi


    if [ "${ENABLE_GPIO_SHUTDOWN}" = "1" ]; then
        if ([ "${ENABLE_I2C}" = "1" ] || [ "${PITFT}" = "1" ]) && [ -z "${GPIO_SHUTDOWN_PIN}" ]; then
            # By default GPIO shutdown uses the same pin as the (master) I2C SCL.
            # If I2C is configured and an alternative pin is not configured for
            # gpio-shutdown, there is a configuration conflict.
            bbfatal "I2C and gpio-shutdown are both enabled and using the same pins!"
        fi
        echo "# Enable gpio-shutdown" >> $CONFIG
        if [ -z "${GPIO_SHUTDOWN_PIN}" ]; then
            echo "dtoverlay=gpio-shutdown" >> $CONFIG
        else
            echo "dtoverlay=gpio-shutdown,gpio_pin=${GPIO_SHUTDOWN_PIN}" >> $CONFIG
        fi
    fi

    # Append extra config if the user has provided any
    printf "${RPI_EXTRA_CONFIG}\n" >> $CONFIG

    # Handle setup with armstub file
    if [ "${@bb.utils.contains("MACHINE_FEATURES", "armstub", "1", "0", d)}" = "1" ]; then
        echo "\n# ARM stub configuration" >> $CONFIG
        echo "armstub=${ARMSTUB}" >> $CONFIG
        case "${ARMSTUB}" in
            *-gic.bin)
                echo  "enable_gic=1" >> $CONFIG
                ;;
        esac
    fi

    # WM8960 support
    if [ "${WM8960}" = "1" ]; then
        echo "# Enable WM8960" >> $CONFIG
        echo "dtoverlay=wm8960-soundcard" >> $CONFIG
    fi

    # W1-GPIO - One-Wire Interface
    if [ "${ENABLE_W1}" = "1" ]; then
        echo "# Enable One-Wire Interface" >> $CONFIG
        echo "dtoverlay=w1-gpio" >> $CONFIG
    fi
}

do_compile:append:raspberrypi3-64() {
    echo "# have a properly sized image" >> $CONFIG
    echo "disable_overscan=1" >> $CONFIG

    echo "# Enable audio (loads snd_bcm2835)" >> $CONFIG
    echo "dtparam=audio=on" >> $CONFIG
}

do_deploy() {
    install -D -m 600 ${B}/config.txt ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/config.txt

    if grep -q -E '^.{80}.$' ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/config.txt; then
        bbwarn "config.txt contains lines longer than 80 characters, this is not supported"
    fi
}

addtask deploy before do_build after do_install
do_deploy[dirs] += "${DEPLOYDIR}/${BOOTFILES_DIR_NAME}"

PACKAGE_ARCH = "${MACHINE_ARCH}"
