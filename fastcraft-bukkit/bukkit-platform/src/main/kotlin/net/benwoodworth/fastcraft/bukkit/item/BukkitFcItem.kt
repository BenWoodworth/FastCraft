package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcItem
import org.bukkit.inventory.ItemStack

interface BukkitFcItem : FcItem {
    val itemStack: ItemStack

    fun toItemStack(): ItemStack

    companion object {
        val FcItem.itemStack: ItemStack
            get() = (this as BukkitFcItem).itemStack

        fun FcItem.toItemStack(): ItemStack {
            return (this as BukkitFcItem).toItemStack()
        }
    }

    interface Factory : FcItem.Factory {
        fun createFcItem(itemStack: ItemStack): FcItem

        companion object {
            fun FcItem.Factory.createFcItem(itemStack: ItemStack): FcItem {
                return (this as Factory).createFcItem(itemStack)
            }
        }
    }
}
