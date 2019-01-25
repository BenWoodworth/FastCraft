package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.locale.FcLocale
import org.bukkit.inventory.ItemStack

interface BukkitFcItemConverter {

    fun ItemStack.toFcItem(): FcItem

    fun FcItem.toItemStack(locale: FcLocale): ItemStack
}