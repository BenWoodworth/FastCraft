package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.bukkit.item.BukkitFcItemTypes.Companion.fromMaterial
import net.benwoodworth.fastcraft.bukkit.text.BukkitLocalizer
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import org.bukkit.Material

open class BukkitFcItemType_1_15(
    material: Material,
    textFactory: FcTextFactory,
    localizer: BukkitLocalizer,
    itemTypes: FcItemTypes,
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
}
