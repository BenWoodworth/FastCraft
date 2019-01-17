package net.benwoodworth.fastcraft.bukkit.bukkit_1_13_R01.item

import net.benwoodworth.fastcraft.bukkit.bukkit_1_13_R01.locale.BukkitFcTranslatable
import net.benwoodworth.fastcraft.platform.locale.FcTranslatable
import org.bukkit.Material

class BukkitFcItemType(
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
