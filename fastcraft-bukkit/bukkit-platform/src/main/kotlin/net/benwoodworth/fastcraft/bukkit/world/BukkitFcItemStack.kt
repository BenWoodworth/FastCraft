package net.benwoodworth.fastcraft.bukkit.world

import net.benwoodworth.fastcraft.platform.world.FcItemStack
import org.bukkit.inventory.ItemStack

object BukkitFcItemStack {
    interface TypeClass : FcItemStack.TypeClass {
        val FcItemStack.bukkitItemStack: ItemStack

        fun FcItemStack.toBukkitItemStack(): ItemStack
    }

    interface Factory : FcItemStack.Factory {
        fun create(itemStack: ItemStack): FcItemStack
    }
}

val FcItemStack.TypeClass.bukkit: BukkitFcItemStack.TypeClass
    get() = this as BukkitFcItemStack.TypeClass

fun FcItemStack.Factory.create(itemStack: ItemStack): FcItemStack {
    return (this as BukkitFcItemStack.Factory).create(itemStack)
}
