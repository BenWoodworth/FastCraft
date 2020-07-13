package net.benwoodworth.fastcraft.bukkit.world

import net.benwoodworth.fastcraft.platform.world.FcItem
import org.bukkit.Material
import javax.inject.Inject


object BukkitFcItem_1_9  {
    class TypeClass @Inject constructor(
        private val tc: BukkitFcItem_1_7.TypeClass,
        private val items: FcItem.Factory,
    ) : BukkitFcItem.TypeClass by tc {
        override val FcItem.craftingRemainingItem: FcItem?
            get() = when (material) {
                Material.DRAGONS_BREATH -> items.fromMaterial(Material.GLASS_BOTTLE)
                else -> tc.run { craftingRemainingItem }
            }
    }
}
