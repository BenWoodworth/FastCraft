package net.benwoodworth.fastcraft.bukkit.world

import net.benwoodworth.fastcraft.bukkit.text.BukkitLocalizer
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.world.FcItem
import org.bukkit.Material
import javax.inject.Inject
import javax.inject.Singleton

object FcItem_Bukkit_1_15 {
    @Singleton
    class Operations @Inject constructor(
        fcTextFactory: FcText.Factory,
        localizer: BukkitLocalizer,
        private val fcItemFactory: FcItem.Factory,
    ) : FcItem_Bukkit_1_13.Operations(
        fcTextFactory = fcTextFactory,
        localizer = localizer,
        fcItemFactory = fcItemFactory,
    ) {
        @Suppress("USELESS_CAST") // IDE thinks it's an error without the cast
        override val FcItem.craftingRemainingItem: FcItem?
            get() = (material as Material).craftingRemainingItem?.let { fcItemFactory.fromMaterial(it) }
    }
}
