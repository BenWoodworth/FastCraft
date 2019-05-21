package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import org.bukkit.Material

interface BukkitFcItemTypes : FcItemTypes {

    fun fromMaterial(material: Material): FcItemType
}
