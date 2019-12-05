package net.benwoodworth.fastcraft.bukkit.recipe

import org.bukkit.inventory.ItemStack

interface IngredientProductProvider {
    fun getIngredientProducts(item: ItemStack): List<ItemStack>
}
