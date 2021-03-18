package net.benwoodworth.fastcraft.bukkit.world

import net.benwoodworth.fastcraft.platform.world.FcItemStack
import org.bukkit.inventory.ItemStack

object FcItemStack_Bukkit {
    interface Operations : FcItemStack.Operations {
        val FcItemStack.itemStack: ItemStack
    }

    interface Factory : FcItemStack.Factory {
        fun create(itemStack: ItemStack): FcItemStack
    }
}

val FcItemStack.Operations.bukkit: FcItemStack_Bukkit.Operations
    get() = this as FcItemStack_Bukkit.Operations

fun FcItemStack.Factory.create(itemStack: ItemStack): FcItemStack {
    return (this as FcItemStack_Bukkit.Factory).create(itemStack)
}
