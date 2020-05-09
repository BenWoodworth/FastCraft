package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcItemStack
import org.bukkit.inventory.ItemStack

interface BukkitFcItemStack : FcItemStack {
    val bukkitItemStack: ItemStack

    fun toBukkitItemStack(): ItemStack

    interface Factory : FcItemStack.Factory {
        fun create(itemStack: ItemStack): FcItemStack
    }
}

val FcItemStack.bukkitItemStack: ItemStack
    get() = (this as BukkitFcItemStack).bukkitItemStack

fun FcItemStack.toBukkitItemStack(): ItemStack {
    return (this as BukkitFcItemStack).toBukkitItemStack()
}

fun FcItemStack.Factory.create(itemStack: ItemStack): FcItemStack {
    return (this as BukkitFcItemStack.Factory).create(itemStack)
}
