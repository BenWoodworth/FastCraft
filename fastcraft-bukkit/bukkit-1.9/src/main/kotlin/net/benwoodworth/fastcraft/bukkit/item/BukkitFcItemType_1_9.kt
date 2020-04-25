package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.bukkit.item.BukkitFcItemTypes.Companion.fromMaterial
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import org.bukkit.Material
import org.bukkit.material.MaterialData


open class BukkitFcItemType_1_9(
    materialData: MaterialData,
    textFactory: FcTextFactory,
    itemTypes: FcItemTypes,
) : BukkitFcItemType_1_7(
    materialData = materialData,
    textFactory = textFactory,
    itemTypes = itemTypes,
) {
    override val craftingResult: FcItemType?
        get() = when (material) {
            Material.DRAGONS_BREATH -> itemTypes.fromMaterial(Material.GLASS_BOTTLE)
            else -> super.craftingResult
        }
}
