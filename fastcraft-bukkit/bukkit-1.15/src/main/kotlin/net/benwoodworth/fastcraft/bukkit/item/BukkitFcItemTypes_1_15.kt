package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.bukkit.text.BukkitLocalizer
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import org.bukkit.Material
import javax.inject.Inject
import javax.inject.Provider

class BukkitFcItemTypes_1_15 @Inject constructor(
    textFactory: FcTextFactory,
    localizer: BukkitLocalizer,
    itemTypes: Provider<FcItemTypes>,
) : BukkitFcItemTypes_1_13(
    textFactory = textFactory,
    localizer = localizer,
    itemTypes = itemTypes,
) {
    override fun fromMaterial(material: Material): FcItemType {
        return BukkitFcItemType_1_15(material, textFactory, localizer, itemTypes.get())
    }
}
