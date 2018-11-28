package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.bukkit.locale.Bukkit_11300R01_FcTranslatable
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.locale.FcTranslatable
import org.bukkit.Material

@Suppress("ClassName")
class Bukkit_11300R01_FcItemType(
    val material: Material
) : FcItemType {

    override val name: FcTranslatable
        get() = Bukkit_11300R01_FcTranslatable(
            with(material.key) { "item.$namespace.$key" },
            material.name // TODO
        )

    override val description: FcTranslatable
        get() = Bukkit_11300R01_FcTranslatable(
            with(material.key) { "item.$namespace.$key.description" },
            "None" // TODO
        )

    override val maxAmount: Int
        get() = material.maxStackSize
}
