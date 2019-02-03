package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.bukkit.locale.BukkitFcTranslatable_1_13_00_R01
import net.benwoodworth.fastcraft.platform.locale.FcTranslatable
import org.bukkit.Material

class BukkitFcItemType_1_13_00_R01(
    override val material: Material
) : BukkitFcItemType {

    override val name: FcTranslatable
        get() = BukkitFcTranslatable_1_13_00_R01(
            with(material.key) { "item.$namespace.$key" }
        )

    override val description: FcTranslatable
        get() = BukkitFcTranslatable_1_13_00_R01(
            with(material.key) { "item.$namespace.$key.description" }
        )

    override val maxAmount: Int
        get() = material.maxStackSize
}
