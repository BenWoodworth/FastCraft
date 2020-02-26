package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.bukkit.text.createFcTextTranslate
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.material.MaterialData

open class BukkitFcItemType_1_8_R01 private constructor(
    override val materialData: MaterialData,
    private val textFactory: FcTextFactory
) : BukkitFcItemType {
    override val material: Material
        get() = materialData.itemType

    override val id: String
        get() = material.toString() // TODO use a better ID

    override val name: FcText
        get() = textFactory.createFcTextTranslate(materialData.toString())

    override val description: FcText
        get() = textFactory.createLegacy("")

    override val maxAmount: Int
        get() = material.maxStackSize

    override fun equals(other: Any?): Boolean {
        return other is FcItemType && materialData == other.materialData
    }

    override fun hashCode(): Int {
        return materialData.hashCode()
    }
}
