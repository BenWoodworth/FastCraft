package net.benwoodworth.fastcraft.bukkit.item

import org.bukkit.enchantments.Enchantment
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

internal inline val ItemStack.displayName: String?
    get() = accessMeta { if (hasDisplayName()) displayName else null }

internal inline val ItemStack.lore: List<String>?
    get() = accessMeta { if (hasLore()) lore else null }

internal inline val ItemStack.enchants: Map<Enchantment, Int>?
    get() = accessMeta { if (hasEnchants()) enchants else null }

internal inline val ItemStack.localizedName: String?
    get() = accessMeta { if (hasLocalizedName()) localizedName else null }


