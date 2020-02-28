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

    private fun Material.getNameLocaleKey(prefix: String): String {
        return "$prefix.${key.namespace}.${key.key}"
    }

    override val itemName: FcText
        get() {
            val prefix = when {
                material.isItem -> "item"
                material.isBlock -> "block"
                else -> "item"
            }
            return textFactory.createFcTextTranslate(material.getNameLocaleKey(prefix))
        }

    override val blockName: FcText
        get() {
            val prefix = when {
                material.isBlock -> "block"
                material.isItem -> "item"
                else -> "block"
            }
            return textFactory.createFcTextTranslate(material.getNameLocaleKey(prefix))
        }

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
