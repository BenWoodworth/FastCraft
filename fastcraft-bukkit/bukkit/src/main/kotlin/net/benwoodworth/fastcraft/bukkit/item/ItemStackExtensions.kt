package net.benwoodworth.fastcraft.bukkit.item

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

fun ItemStack.getItemMetaOrNull(): ItemMeta? {
    return when {
        hasItemMeta() -> itemMeta
        else -> null
    }
}
