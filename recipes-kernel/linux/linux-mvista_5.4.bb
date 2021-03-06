MV_KERNEL_BRANCH ?= "mvl-5.4/msd.pi"
MV_KERNEL_TREE ?= "git://github.com/MontaVista-OpenSourceTechnology/linux-mvista.git;protocol=https"
MV_KERNELCACHE_BRANCH ?= "yocto-5.4"
MV_KERNELCACHE_TREE ?= "git://github.com/MontaVista-OpenSourceTechnology/yocto-kernel-cache;protocol=https"

require recipes-kernel/linux/linux-yocto.inc

SRCREV_machine ?= "${MV_KERNEL_BRANCH}"
SRCREV_meta ?= "${MV_KERNELCACHE_BRANCH}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

S = "${WORKDIR}/git"

LINUX_VERSION = "5.4"
KERNEL_VERSION_SANITY_SKIP="1"
PV = "${LINUX_VERSION}+git${SRCPV}"

SRC_URI = "${MV_KERNEL_TREE};branch=${MV_KERNEL_BRANCH};name=machine \
           ${MV_KERNELCACHE_TREE};type=kmeta;name=meta;branch=${MV_KERNELCACHE_BRANCH};destsuffix=${KMETA}"
SRC_URI += "file://defconfig"

DEPENDS += "elfutils-native"

KMETA = "kernel-meta"
KCONF_BSP_AUDIT_LEVEL = "0"
COMPATIBLE_MACHINE = "null"
COMPATIBLE_MACHINE_raspberrypi4-64 = "raspberrypi4-64"
COMPATIBLE_MACHINE_raspberrypi4-64-xen = "raspberrypi4-64-xen"

# Xen-specific stuff below
FILESEXTRAPATHS_prepend_rpixen := "${THISDIR}/files:"

SRC_URI_append_rpixen += "file://xen.cfg"

do_deploy_append_rpixen() {
    # Override the cmdline.txt to be what we need.
    echo "console=hvc0 clk_ignore_unused root=/dev/mmcblk0p2 rootwait" >${DEPLOYDIR}/bcm2835-bootfiles/cmdline.txt
}
