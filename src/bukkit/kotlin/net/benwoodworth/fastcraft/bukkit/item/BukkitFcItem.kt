package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcItem
import org.bukkit.inventory.ItemStack

interface BukkitFcItem : FcItem {
    fun toItemStack(): ItemStack
}

fun FcItem.toItemStack(): ItemStack {
    return (this as BukkitFcItem).toItemStack()
}
