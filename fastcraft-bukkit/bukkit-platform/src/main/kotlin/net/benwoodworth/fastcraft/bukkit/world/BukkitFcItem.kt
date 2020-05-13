package net.benwoodworth.fastcraft.bukkit.world

import net.benwoodworth.fastcraft.platform.world.FcItem
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

interface BukkitFcItem : FcItem {
    val material: Material
    val materialData: Any?

    fun toItemStack(amount: Int = 1): ItemStack

    interface Factory : FcItem.Factory {
        fun fromMaterial(material: Material): FcItem

        fun fromMaterialData(materialData: Any): FcItem
    }
}

val FcItem.material: Material
    get() = (this as BukkitFcItem).material

val FcItem.materialData: Any?
    get() = (this as BukkitFcItem).materialData

fun FcItem.toItemStack(amount: Int = 1): ItemStack {
    return (this as BukkitFcItem).toItemStack(amount)
}

fun FcItem.Factory.fromMaterial(material: Material): FcItem {
    return (this as BukkitFcItem.Factory).fromMaterial(material)
}

fun FcItem.Factory.fromMaterialData(materialData: Any): FcItem {
    return (this as BukkitFcItem.Factory).fromMaterialData(materialData)
}
