package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcItem
import org.bukkit.inventory.ItemStack

interface BukkitFcItem : FcItem {
    val itemStack: ItemStack

    fun toItemStack(): ItemStack
}

val FcItem.itemStack: ItemStack
    get() = (this as BukkitFcItem).itemStack

fun FcItem.toItemStack(): ItemStack {
    return (this as BukkitFcItem).toItemStack()
}
