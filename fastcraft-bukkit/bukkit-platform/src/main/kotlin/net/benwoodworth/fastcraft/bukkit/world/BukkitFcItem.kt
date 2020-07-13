package net.benwoodworth.fastcraft.bukkit.world

import net.benwoodworth.fastcraft.platform.world.FcItem
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.material.MaterialData

object BukkitFcItem {
    interface TypeClass : FcItem.TypeClass {
        val FcItem.material: Material
        val FcItem.materialData: MaterialData?

        fun FcItem.toItemStack(amount: Int = 1): ItemStack
    }

    interface Factory : FcItem.Factory {
        fun fromMaterial(material: Material): FcItem

        fun fromMaterialData(materialData: MaterialData): FcItem
    }
}

val FcItem.TypeClass.bukkit: BukkitFcItem.TypeClass
    get() = this as BukkitFcItem.TypeClass

fun FcItem.Factory.fromMaterial(material: Material): FcItem {
    return (this as BukkitFcItem.Factory).fromMaterial(material)
}

fun FcItem.Factory.fromMaterialData(materialData: Any): FcItem {
    return (this as BukkitFcItem.Factory).fromMaterialData(materialData)
}
