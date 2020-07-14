package net.benwoodworth.fastcraft.bukkit.world

import net.benwoodworth.fastcraft.platform.world.FcItem
import org.bukkit.Material
import javax.inject.Inject
import javax.inject.Singleton


object BukkitFcItem_1_9  {
    @Singleton
    class TypeClass @Inject constructor(
        private val tc_1_7: BukkitFcItem_1_7.TypeClass,
        private val items: FcItem.Factory,
    ) : BukkitFcItem.TypeClass by tc_1_7 {
        override val FcItem.craftingRemainingItem: FcItem?
            get() = when (material) {
                Material.DRAGONS_BREATH -> items.fromMaterial(Material.GLASS_BOTTLE)
                else -> tc_1_7.run { craftingRemainingItem }
            }
    }
}
