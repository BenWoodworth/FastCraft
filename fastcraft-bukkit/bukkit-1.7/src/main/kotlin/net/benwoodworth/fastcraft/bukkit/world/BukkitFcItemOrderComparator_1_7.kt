package net.benwoodworth.fastcraft.bukkit.world

import net.benwoodworth.fastcraft.platform.world.FcItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BukkitFcItemOrderComparator_1_7 @Inject constructor(
    private val fcItemTypeClass: FcItem.TypeClass,
) : BukkitFcItemOrderComparator {
    override fun compare(item0: FcItem, item1: FcItem): Int {
        @Suppress("DEPRECATION")
        return fcItemTypeClass.bukkit.run { item0.material.id - item1.material.id }
    }
}
