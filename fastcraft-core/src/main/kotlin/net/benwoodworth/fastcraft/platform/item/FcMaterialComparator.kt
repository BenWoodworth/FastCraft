package net.benwoodworth.fastcraft.platform.item

interface FcMaterialComparator : Comparator<FcMaterial> {
    override fun compare(type0: FcMaterial, type1: FcMaterial): Int
}
