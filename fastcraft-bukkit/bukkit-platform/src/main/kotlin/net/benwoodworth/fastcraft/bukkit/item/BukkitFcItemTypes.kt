package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import org.bukkit.Material

interface BukkitFcItemTypes : FcItemTypes {
    fun fromMaterial(material: Material): FcItemType

    fun fromMaterialData(materialData: Any): FcItemType

    companion object {
        fun FcItemTypes.fromMaterial(material: Material): FcItemType {
            return (this as BukkitFcItemTypes).fromMaterial(material)
        }

        fun FcItemTypes.fromMaterialData(materialData: Any): FcItemType {
            return (this as BukkitFcItemTypes).fromMaterialData(materialData)
        }
    }
}
