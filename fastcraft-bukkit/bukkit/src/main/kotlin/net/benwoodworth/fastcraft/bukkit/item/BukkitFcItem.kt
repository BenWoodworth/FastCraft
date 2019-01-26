package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcItem
import org.bukkit.inventory.ItemStack

interface BukkitFcItem : FcItem {

    fun cloneItemStack(): ItemStack
}
