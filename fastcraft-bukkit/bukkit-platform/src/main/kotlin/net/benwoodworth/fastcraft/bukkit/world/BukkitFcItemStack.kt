package net.benwoodworth.fastcraft.bukkit.world

import net.benwoodworth.fastcraft.platform.world.FcItemStack
import org.bukkit.inventory.ItemStack

object BukkitFcItemStack {
    interface Operations : FcItemStack.Operations {
        val FcItemStack.itemStack: ItemStack
    }

    interface Factory : FcItemStack.Factory {
        fun create(itemStack: ItemStack): FcItemStack
    }
}

val FcItemStack.Operations.bukkit: BukkitFcItemStack.Operations
    get() = this as BukkitFcItemStack.Operations

fun FcItemStack.Factory.create(itemStack: ItemStack): FcItemStack {
    return (this as BukkitFcItemStack.Factory).create(itemStack)
}
