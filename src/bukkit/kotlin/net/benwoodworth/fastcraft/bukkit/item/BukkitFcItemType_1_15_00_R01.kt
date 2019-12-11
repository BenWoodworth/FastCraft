package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.bukkit.text.createFcTextTranslate
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import org.bukkit.Material

class BukkitFcItemType_1_15_00_R01(
    override val material: Material,
    private val textFactory: FcTextFactory
) : BukkitFcItemType {
    override val name: FcText
        get() = textFactory.createFcTextTranslate(
            material.key.run { "item.$namespace.$key" }
        )

    override val description: FcText
        get() = textFactory.createFcTextTranslate(
            material.key.run { "item.$namespace.$key.description" }
        )

    override val maxAmount: Int
        get() = material.maxStackSize

    override fun equals(other: Any?): Boolean {
        return other is FcItemType && material == other.material
    }

    override fun hashCode(): Int {
        return material.hashCode()
    }
}
