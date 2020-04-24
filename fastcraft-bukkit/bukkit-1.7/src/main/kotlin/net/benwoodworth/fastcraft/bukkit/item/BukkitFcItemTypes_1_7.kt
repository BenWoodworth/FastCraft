package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import org.bukkit.Material
import org.bukkit.material.MaterialData
import javax.inject.Inject

open class BukkitFcItemTypes_1_7 @Inject constructor(
    private val textFactory: FcTextFactory,
) : BukkitFcItemTypes {
    override val air: FcItemType by lazy {
        fromMaterial(Material.AIR)
    }

    override val ironSword: FcItemType by lazy {
        fromMaterial(Material.IRON_SWORD)
    }

    override val craftingTable: FcItemType by lazy {
        fromMaterial(Material.WORKBENCH)
    }

    override val anvil: FcItemType by lazy {
        fromMaterial(Material.ANVIL)
    }

    override val netherStar: FcItemType by lazy {
        fromMaterial(Material.NETHER_STAR)
    }

    override fun fromMaterial(material: Material): FcItemType {
        return fromMaterialData(MaterialData(material))
    }

    override fun fromMaterialData(materialData: Any): FcItemType {
        require(materialData is MaterialData)
        return BukkitFcItemType_1_7(materialData, textFactory)
    }
}
