package net.benwoodworth.fastcraft.platform.world

interface FcMaterialComparator : Comparator<FcItem> {
    override fun compare(type0: FcItem, type1: FcItem): Int //TODO FcMaterial
}
