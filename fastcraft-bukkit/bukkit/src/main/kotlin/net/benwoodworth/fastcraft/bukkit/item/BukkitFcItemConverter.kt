package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.text.FcLocale
import org.bukkit.inventory.ItemStack

interface BukkitFcItemConverter {

    fun BukkitFcItem.toItemStack(locale: FcLocale): ItemStack
}