package net.benwoodworth.fastcraft.bukkit.world

import net.benwoodworth.fastcraft.platform.world.FcItem
import org.bukkit.Material
import javax.inject.Inject
import javax.inject.Singleton

object BukkitFcItem_1_15 {
    @Singleton
    class TypeClass @Inject constructor(
        private val tc: BukkitFcItem_1_13.TypeClass,
        private val items: FcItem.Factory,
    ) : BukkitFcItem.TypeClass by tc {
        override val FcItem.craftingRemainingItem: FcItem?
            get() = (material as Material).craftingRemainingItem?.let { items.fromMaterial(it) }
    }
}
