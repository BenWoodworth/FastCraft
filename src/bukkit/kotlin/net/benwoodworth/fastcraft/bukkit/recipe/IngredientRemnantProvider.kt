package net.benwoodworth.fastcraft.bukkit.recipe

import org.bukkit.inventory.ItemStack

interface IngredientRemnantProvider {
    fun getRemnants(ingredient: ItemStack): List<ItemStack>
}
