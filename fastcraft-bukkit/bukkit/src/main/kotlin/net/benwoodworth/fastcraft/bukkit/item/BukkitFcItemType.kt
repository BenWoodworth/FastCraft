package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcItemType
import org.bukkit.Material

interface BukkitFcItemType : FcItemType {

    val material: Material
}
