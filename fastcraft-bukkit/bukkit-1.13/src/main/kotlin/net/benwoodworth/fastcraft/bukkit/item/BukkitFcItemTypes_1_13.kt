package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.bukkit.text.BukkitLocalizer
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import org.bukkit.Material
import javax.inject.Inject
import javax.inject.Provider

open class BukkitFcItemTypes_1_13 @Inject constructor(
    textFactory: FcTextFactory,
    itemTypes: Provider<FcItemTypes>,
    protected val localizer: BukkitLocalizer,
) : BukkitFcItemTypes_1_9(
    textFactory = textFactory,
    itemTypes = itemTypes,
) {
    override val craftingTable: FcItemType by lazy {
        fromMaterial(Material.CRAFTING_TABLE)
    }

    override fun fromMaterial(material: Material): FcItemType {
        return BukkitFcItemType_1_13(material, textFactory, localizer, itemTypes.get())
    }

    override fun fromMaterialData(materialData: Any): FcItemType {
        throw UnsupportedOperationException()
    }
}
