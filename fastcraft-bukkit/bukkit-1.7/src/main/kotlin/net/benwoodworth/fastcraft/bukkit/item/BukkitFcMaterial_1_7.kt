package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcMaterial
import net.benwoodworth.fastcraft.platform.text.FcText
import org.apache.commons.lang.WordUtils
import org.bukkit.Material
import org.bukkit.material.MaterialData
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton


open class BukkitFcMaterial_1_7(
    override val materialData: MaterialData,
    protected val textFactory: FcText.Factory,
    protected val materials: FcMaterial.Factory,
) : BukkitFcMaterial {
    @Suppress("DEPRECATION")
    override val id: String
        get() = "${materialData.itemTypeId}:${materialData.data}"

    override val material: Material
        get() = materialData.itemType

    override val itemName: FcText
        get() = textFactory.createLegacy(materialData.getName())

    override val blockName: FcText
        get() = itemName

    override val maxAmount: Int
        get() = material.maxStackSize

    override val craftingResult: FcMaterial?
        get() = when (material) {
            Material.LAVA_BUCKET,
            Material.MILK_BUCKET,
            Material.WATER_BUCKET,
            -> materials.fromMaterial(Material.BUCKET)

            else -> null
        }

    override fun equals(other: Any?): Boolean {
        return other is FcMaterial && materialData == other.materialData
    }

    override fun hashCode(): Int {
        return materialData.hashCode()
    }

    private fun MaterialData.getName(): String {
        var name = toString()

        // Trim data number from end
        val nameEnd = name.lastIndexOf('(')
        if (nameEnd != -1) {
            name = name.substring(0 until nameEnd)
        }

        name = name.replace('_', ' ')

        return WordUtils.capitalizeFully(name)
    }

    @Singleton
    open class Factory @Inject constructor(
        protected val textFactory: FcText.Factory,
        protected val materials: Provider<FcMaterial.Factory>,
    ) : BukkitFcMaterial.Factory {
        override val air: FcMaterial by lazy { fromMaterial(Material.AIR) }
        override val ironSword: FcMaterial by lazy { fromMaterial(Material.IRON_SWORD) }
        override val craftingTable: FcMaterial by lazy { fromMaterial(Material.WORKBENCH) }
        override val anvil: FcMaterial by lazy { fromMaterial(Material.ANVIL) }
        override val netherStar: FcMaterial by lazy { fromMaterial(Material.NETHER_STAR) }

        override fun fromMaterial(material: Material): FcMaterial {
            return fromMaterialData(MaterialData(material))
        }

        override fun fromMaterialData(materialData: Any): FcMaterial {
            require(materialData is MaterialData)
            return BukkitFcMaterial_1_7(materialData, textFactory, materials.get())
        }
    }
}
