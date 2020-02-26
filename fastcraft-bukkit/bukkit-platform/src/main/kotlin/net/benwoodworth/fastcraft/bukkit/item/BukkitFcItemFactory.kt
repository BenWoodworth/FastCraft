package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemFactory
import org.bukkit.inventory.ItemStack

interface BukkitFcItemFactory : FcItemFactory {
    fun createFcItem(itemStack: ItemStack): FcItem
}

fun FcItemFactory.createFcItem(itemStack: ItemStack): FcItem {
    return (this as BukkitFcItemFactory).createFcItem(itemStack)
}
