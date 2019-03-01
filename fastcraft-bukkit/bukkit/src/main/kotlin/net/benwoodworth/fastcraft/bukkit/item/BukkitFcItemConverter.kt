package net.benwoodworth.fastcraft.bukkit.item

import org.bukkit.inventory.ItemStack

interface BukkitFcItemConverter {

    fun BukkitFcItem.toItemStack(): ItemStack
}