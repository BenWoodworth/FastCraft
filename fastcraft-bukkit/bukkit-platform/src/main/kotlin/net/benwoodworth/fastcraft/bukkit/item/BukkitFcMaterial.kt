package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcMaterial
import org.bukkit.Material

interface BukkitFcMaterial : FcMaterial {
    val material: Material
    val materialData: Any?

    interface Factory : FcMaterial.Factory {
        fun fromMaterial(material: Material): FcMaterial

        fun fromMaterialData(materialData: Any): FcMaterial
    }
}

val FcMaterial.material: Material
    get() = (this as BukkitFcMaterial).material

val FcMaterial.materialData: Any?
    get() = (this as BukkitFcMaterial).materialData

fun FcMaterial.Factory.fromMaterial(material: Material): FcMaterial {
    return (this as BukkitFcMaterial.Factory).fromMaterial(material)
}

fun FcMaterial.Factory.fromMaterialData(materialData: Any): FcMaterial {
    return (this as BukkitFcMaterial.Factory).fromMaterialData(materialData)
}
