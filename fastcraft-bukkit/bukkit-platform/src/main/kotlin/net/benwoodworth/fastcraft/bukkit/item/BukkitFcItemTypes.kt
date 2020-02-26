package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import org.bukkit.Material
import org.bukkit.material.MaterialData

interface BukkitFcItemTypes : FcItemTypes {
    fun fromMaterial(material: Material): FcItemType

    fun fromMaterialData(materialData: Any): FcItemType
}

fun FcItemTypes.fromMaterial(material: Material): FcItemType {
    return (this as BukkitFcItemTypes).fromMaterial(material)
}
