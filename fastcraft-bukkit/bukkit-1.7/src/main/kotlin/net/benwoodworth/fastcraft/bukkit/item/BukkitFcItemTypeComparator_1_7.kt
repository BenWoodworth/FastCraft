package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcItemType
import javax.inject.Inject

class BukkitFcItemTypeComparator_1_7 @Inject constructor(
) : BukkitFcItemTypeComparator {
    override fun compare(type0: FcItemType, type1: FcItemType): Int {
        @Suppress("DEPRECATION")
        return type0.material.id - type1.material.id
    }
}
