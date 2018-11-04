package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.bukkit.text.Bukkit_11300R01_FcTextBuilder
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.text.FcText
import org.bukkit.Material

@Suppress("ClassName")
class Bukkit_11300R01_FcItemType(
    val material: Material
) : FcItemType {

    override val name: FcText
        get() = Bukkit_11300R01_FcTextBuilder()
            .text(material.toString())
            .build()
}
