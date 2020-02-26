package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.bukkit.text.createFcTextTranslate
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import org.bukkit.Material

class BukkitFcItemType_1_13_R01(
    override val material: Material,
    val textFactory: FcTextFactory
) : BukkitFcItemType {
    override val materialData: Nothing?
        get() = null

    override val id: String
        get() = material.key.toString()

    private val Material.localeKey: String
        get() {
            val type = when {
                isBlock -> "block"
                isItem -> "item"
                else -> "?"
            }
            return "$type.${key.namespace}.${key.key}"
        }

    override val name: FcText
        get() = textFactory.createFcTextTranslate(material.localeKey)

    override val description: FcText
        get() = textFactory.createFcTextTranslate(
            "${material.localeKey}.description"
        )

    override val maxAmount: Int
        get() = material.maxStackSize

    override fun equals(other: Any?): Boolean {
        return other is FcItemType &&
                material == other.material &&
                materialData == other.materialData
    }

    override fun hashCode(): Int {
        return material.hashCode()
    }
}
