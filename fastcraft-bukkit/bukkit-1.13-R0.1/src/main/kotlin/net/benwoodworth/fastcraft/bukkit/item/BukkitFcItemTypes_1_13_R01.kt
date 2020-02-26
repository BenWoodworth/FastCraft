package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import org.bukkit.Material
import javax.inject.Inject

class BukkitFcItemTypes_1_13_R01 @Inject constructor(
    private val textFactory: FcTextFactory
) : BukkitFcItemTypes_1_8_R01(
    textFactory = textFactory
) {
    override val craftingTable: FcItemType by lazy {
        fromMaterial(Material.CRAFTING_TABLE)
    }

    override fun fromMaterial(material: Material): FcItemType {
        return BukkitFcItemType_1_13_R01(material, textFactory)
    }

    override fun fromMaterialData(materialData: Any): FcItemType {
        throw UnsupportedOperationException()
    }
}
