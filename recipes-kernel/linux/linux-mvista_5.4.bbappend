FILESEXTRAPATHS_prepend := "${THISDIR}/linux-mvista:"
SRC_URI += "file://defconfig"

COMPATIBLE_MACHINE_raspberrypi4-64 = "raspberrypi4-64"
COMPATIBLE_MACHINE_raspberrypi4-64-xen = "raspberrypi4-64-xen"

MV_KERNEL_BRANCH = "mvl-5.4/msd.pi"
