package net.benwoodworth.fastcraft.bukkit.recipe

import org.bukkit.inventory.ItemStack

interface IngredientReminantProvider {
    fun getIngredientProducts(item: ItemStack): List<ItemStack>
}
