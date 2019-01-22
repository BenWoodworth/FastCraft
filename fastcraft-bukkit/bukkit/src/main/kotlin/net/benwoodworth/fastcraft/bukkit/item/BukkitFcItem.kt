package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.locale.FcLocale
import org.bukkit.inventory.ItemStack

interface BukkitFcItem : FcItem {

    val base: ItemStack

    fun toItemStack(item: FcItem, locale: FcLocale): ItemStack
}
