package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcItemType
import org.bukkit.Material

interface BukkitFcItemType : FcItemType {
    val material: Material
    val materialData: Any?

    interface Factory : FcItemType.Factory {
        fun fromMaterial(material: Material): FcItemType

        fun fromMaterialData(materialData: Any): FcItemType
    }
}

val FcItemType.material: Material
    get() = (this as BukkitFcItemType).material

val FcItemType.materialData: Any?
    get() = (this as BukkitFcItemType).materialData

fun FcItemType.Factory.fromMaterial(material: Material): FcItemType {
    return (this as BukkitFcItemType.Factory).fromMaterial(material)
}

fun FcItemType.Factory.fromMaterialData(materialData: Any): FcItemType {
    return (this as BukkitFcItemType.Factory).fromMaterialData(materialData)
}
