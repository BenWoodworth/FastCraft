package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcItemType
import org.bukkit.Material

interface BukkitFcItemType : FcItemType {
    val material: Material
    val materialData: Any?

    companion object {
        val FcItemType.material: Material
            get() = (this as BukkitFcItemType).material

        val FcItemType.materialData: Any?
            get() = (this as BukkitFcItemType).materialData
    }

    interface Factory : FcItemType.Factory {
        fun fromMaterial(material: Material): FcItemType

        fun fromMaterialData(materialData: Any): FcItemType

        companion object {
            fun FcItemType.Factory.fromMaterial(material: Material): FcItemType {
                return (this as Factory).fromMaterial(material)
            }

            fun FcItemType.Factory.fromMaterialData(materialData: Any): FcItemType {
                return (this as Factory).fromMaterialData(materialData)
            }
        }
    }
}
