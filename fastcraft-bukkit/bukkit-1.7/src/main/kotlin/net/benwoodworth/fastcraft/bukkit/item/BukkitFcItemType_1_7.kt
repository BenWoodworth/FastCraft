package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.bukkit.item.BukkitFcItemType.Companion.materialData
import net.benwoodworth.fastcraft.bukkit.item.BukkitFcItemTypes.Companion.fromMaterial
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import org.apache.commons.lang.WordUtils
import org.bukkit.Material
import org.bukkit.material.MaterialData


open class BukkitFcItemType_1_7(
    override val materialData: MaterialData,
    protected val textFactory: FcTextFactory,
    protected val itemTypes: FcItemTypes,
) : BukkitFcItemType {
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

    override val craftingResult: FcItemType?
        get() = when (material) {
            Material.LAVA_BUCKET,
            Material.MILK_BUCKET,
            Material.WATER_BUCKET,
            -> itemTypes.fromMaterial(Material.BUCKET)

            else -> null
        }

    override fun equals(other: Any?): Boolean {
        return other is FcItemType && materialData == other.materialData
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
}
