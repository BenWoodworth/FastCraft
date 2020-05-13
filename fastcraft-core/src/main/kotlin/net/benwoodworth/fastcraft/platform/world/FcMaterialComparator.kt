package net.benwoodworth.fastcraft.platform.world

interface FcMaterialComparator : Comparator<FcMaterial> {
    override fun compare(type0: FcMaterial, type1: FcMaterial): Int
}
