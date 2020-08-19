package net.benwoodworth.fastcraft.bukkit.world

import net.benwoodworth.fastcraft.bukkit.text.BukkitLocalizer
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.world.FcItem
import org.bukkit.Material
import javax.inject.Inject
import javax.inject.Singleton

object BukkitFcItem_1_15 {
    @Singleton
    class TypeClass @Inject constructor(
        fcTextFactory: FcText.Factory,
        localizer: BukkitLocalizer,
        private val fcItemFactory: FcItem.Factory,
    ) : BukkitFcItem_1_13.TypeClass(
        fcTextFactory = fcTextFactory,
        localizer = localizer,
        fcItemFactory = fcItemFactory,
    ) {
        override val FcItem.craftingRemainingItem: FcItem?
            get() = (material as Material).craftingRemainingItem?.let { fcItemFactory.fromMaterial(it) }
    }
}
