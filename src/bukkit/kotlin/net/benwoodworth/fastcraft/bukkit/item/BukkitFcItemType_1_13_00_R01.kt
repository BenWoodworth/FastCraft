package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.bukkit.bukkit
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import org.bukkit.Material

class BukkitFcItemType_1_13_00_R01(
    override val material: Material,
    private val textFactory: FcTextFactory
) : BukkitFcItemType {
    override val name: FcText
        get() = textFactory.bukkit.createFcTextTranslate(
            material.key.run { "item.$namespace.$key" }
        )

    override val description: FcText
        get() = textFactory.bukkit.createFcTextTranslate(
            material.key.run { "item.$namespace.$key.description" }
        )

    override val maxAmount: Int
        get() = material.maxStackSize
}
