package net.benwoodworth.fastcraft.bukkit.bukkit_1_13_R01.item

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

internal inline fun ItemStack.updateMeta(update: ItemMeta.() -> Unit) {
    itemMeta = itemMeta.apply(update)
}

internal inline fun <T> ItemStack.accessMeta(access: ItemMeta.() -> T): T? {
    return when (hasItemMeta()) {
        true -> access(itemMeta)
        else -> null
    }
}

internal inline var ItemStack.displayName: String?
    get() = accessMeta { if (hasDisplayName()) displayName else null }
    set(value) {
        accessMeta { displayName = value }
    }

internal inline var ItemStack.lore: List<String>?
    get() = accessMeta { if (hasLore()) lore else null }
    set(value) {
        accessMeta { lore = value }
    }
