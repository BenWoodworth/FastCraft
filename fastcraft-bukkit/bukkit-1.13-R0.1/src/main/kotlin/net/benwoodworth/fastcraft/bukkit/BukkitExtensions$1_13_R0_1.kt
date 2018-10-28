package net.benwoodworth.fastcraft.bukkit

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

internal inline fun ItemStack.updateMeta(update: ItemMeta.() -> Unit) {
    itemMeta = itemMeta.apply(update)
}
