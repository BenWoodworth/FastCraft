package net.benwoodworth.fastcraft.bukkit.world

import net.benwoodworth.fastcraft.bukkit.text.BukkitLocalizer
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.world.FcItem
import org.bukkit.Material
import org.bukkit.Server
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

open class BukkitFcItem_1_15(
    material: Material,
    textFactory: FcText.Factory,
    localizer: BukkitLocalizer,
    items: FcItem.Factory,
) : BukkitFcItem_1_13(
    material = material,
    textFactory = textFactory,
    localizer = localizer,
    items = items,
) {
    override val craftingRemainingItem: FcItem?
        get() = when (material) {
            Material.HONEY_BOTTLE -> items.fromMaterial(Material.GLASS_BOTTLE)
            else -> super.craftingRemainingItem
        }

    @Singleton
    class Factory @Inject constructor(
        textFactory: FcText.Factory,
        localizer: BukkitLocalizer,
        items: Provider<FcItem.Factory>,
        server: Server,
    ) : BukkitFcItem_1_13.Factory(
        textFactory = textFactory,
        localizer = localizer,
        items = items,
        server = server,
    ) {
        override fun fromMaterial(material: Material): FcItem {
            return BukkitFcItem_1_15(material, textFactory, localizer, items.get())
        }
    }
}
