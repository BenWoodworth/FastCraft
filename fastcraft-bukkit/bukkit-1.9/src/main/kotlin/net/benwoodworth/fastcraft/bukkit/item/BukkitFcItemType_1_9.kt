package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import org.bukkit.Material
import org.bukkit.material.MaterialData
import javax.inject.Inject
import javax.inject.Provider


open class BukkitFcItemType_1_9(
    materialData: MaterialData,
    textFactory: FcTextFactory,
    itemTypes: FcItemType.Factory,
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

    open class Factory @Inject constructor(
        textFactory: FcTextFactory,
        itemTypes: Provider<FcItemType.Factory>,
    ) : BukkitFcItemType_1_7.Factory(
        textFactory = textFactory,
        itemTypes = itemTypes,
    ) {
        override fun fromMaterialData(materialData: Any): FcItemType {
            require(materialData is MaterialData)
            return BukkitFcItemType_1_9(materialData, textFactory, itemTypes.get())
        }
    }
}
