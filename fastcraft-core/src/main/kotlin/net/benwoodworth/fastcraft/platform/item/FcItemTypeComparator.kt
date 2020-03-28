package net.benwoodworth.fastcraft.platform.item

interface FcItemTypeComparator : Comparator<FcItemType> {
    override fun compare(type0: FcItemType, type1: FcItemType): Int
}