package net.benwoodworth.fastcraft.bukkit.util

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

inline fun ItemStack.updateMeta(modify: ItemMeta.() -> Unit) {
    itemMeta?.apply {
        modify()
        itemMeta = this
    }
}
