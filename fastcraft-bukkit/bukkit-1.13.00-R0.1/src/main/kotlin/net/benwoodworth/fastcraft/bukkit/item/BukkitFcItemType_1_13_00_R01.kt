package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.locale.FcTranslatable
import org.bukkit.Material

class BukkitFcItemType_1_13_00_R01(
    val material: Material
) : BukkitFcItemType {

    override val name: FcTranslatable
        get() = BukkitFcTranslatable(
            with(material.key) { "item.$namespace.$key" },
            material.name // TODO
        )

    override val description: FcTranslatable
        get() = BukkitFcTranslatable(
            with(material.key) { "item.$namespace.$key.description" },
            "None" // TODO
        )

    override val maxAmount: Int
        get() = material.maxStackSize
}
