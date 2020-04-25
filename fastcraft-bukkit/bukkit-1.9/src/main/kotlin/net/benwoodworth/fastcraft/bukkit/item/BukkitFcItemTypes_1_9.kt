package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import org.bukkit.material.MaterialData
import javax.inject.Inject
import javax.inject.Provider

open class BukkitFcItemTypes_1_9 @Inject constructor(
    textFactory: FcTextFactory,
    itemTypes: Provider<FcItemTypes>,
) : BukkitFcItemTypes_1_7(
    textFactory = textFactory,
    itemTypes = itemTypes,
) {
    override fun fromMaterialData(materialData: Any): FcItemType {
        require(materialData is MaterialData)
        return BukkitFcItemType_1_9(materialData, textFactory, itemTypes.get())
    }
}
