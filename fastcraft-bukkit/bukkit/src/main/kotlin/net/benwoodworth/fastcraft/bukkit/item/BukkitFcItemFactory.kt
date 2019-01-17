package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemFactory
import org.bukkit.inventory.ItemStack

interface BukkitFcItemFactory : FcItemFactory {

    fun fromItemStack(itemStack: ItemStack): FcItem
}
