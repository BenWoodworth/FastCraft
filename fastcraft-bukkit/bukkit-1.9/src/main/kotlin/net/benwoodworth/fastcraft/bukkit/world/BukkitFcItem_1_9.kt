package net.benwoodworth.fastcraft.bukkit.world

import net.benwoodworth.fastcraft.platform.world.FcItem
import org.bukkit.Material
import javax.inject.Inject
import javax.inject.Singleton

object BukkitFcItem_1_9  {
    @Singleton
    class TypeClass @Inject constructor(
        private val items: FcItem.Factory,
        legacyMaterialInfo: LegacyMaterialInfo,
    ) : BukkitFcItem_1_7.TypeClass(
        items = items,
        legacyMaterialInfo = legacyMaterialInfo
    ) {
        override fun craftingRemainingItem(item: FcItem): FcItem? {
            return when (item.material) {
                Material.DRAGONS_BREATH -> items.fromMaterial(Material.GLASS_BOTTLE)
                else -> super.craftingRemainingItem(item)
            }
        }
    }
}
