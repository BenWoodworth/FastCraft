package net.benwoodworth.fastcraft.platform.world

interface FcItemOrderComparator : Comparator<FcItem> {
    override fun compare(item0: FcItem, item1: FcItem): Int //TODO FcMaterial
}
