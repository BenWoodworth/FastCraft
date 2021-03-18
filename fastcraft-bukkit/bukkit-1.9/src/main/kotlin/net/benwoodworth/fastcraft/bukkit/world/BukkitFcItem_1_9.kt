package net.benwoodworth.fastcraft.bukkit.world

import net.benwoodworth.fastcraft.platform.world.FcItem
import org.bukkit.Material
import javax.inject.Inject
import javax.inject.Singleton

object FcItem_Bukkit_1_9 {
    @Singleton
    class Operations @Inject constructor(
        private val fcItemFactory: FcItem.Factory,
        legacyMaterialInfo: LegacyMaterialInfo,
    ) : FcItem_Bukkit_1_7.Operations(
        fcItemFactory = fcItemFactory,
        legacyMaterialInfo = legacyMaterialInfo,
    ) {
        override fun craftingRemainingItem(item: FcItem): FcItem? {
            return when (item.material) {
                Material.DRAGONS_BREATH -> fcItemFactory.fromMaterial(Material.GLASS_BOTTLE)
                else -> super.craftingRemainingItem(item)
            }
        }
    }
}
