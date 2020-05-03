package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.bukkit.text.BukkitLocalizer
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import org.bukkit.Material
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

open class BukkitFcItemType_1_15(
    material: Material,
    textFactory: FcTextFactory,
    localizer: BukkitLocalizer,
    itemTypes: FcItemType.Factory,
) : BukkitFcItemType_1_13(
    material = material,
    textFactory = textFactory,
    localizer = localizer,
    itemTypes = itemTypes,
) {
    override val craftingResult: FcItemType?
        get() = when (material) {
            Material.HONEY_BOTTLE -> itemTypes.fromMaterial(Material.GLASS_BOTTLE)
            else -> super.craftingResult
        }

    @Singleton
    class Factory @Inject constructor(
        textFactory: FcTextFactory,
        localizer: BukkitLocalizer,
        itemTypes: Provider<FcItemType.Factory>,
    ) : BukkitFcItemType_1_13.Factory(
        textFactory = textFactory,
        localizer = localizer,
        itemTypes = itemTypes,
    ) {
        override fun fromMaterial(material: Material): FcItemType {
            return BukkitFcItemType_1_15(material, textFactory, localizer, itemTypes.get())
        }
    }
}
