package net.benwoodworth.fastcraft.implementations.bukkit.recipe

import net.benwoodworth.fastcraft.dependencies.recipe.FcCraftingRecipe
import net.benwoodworth.fastcraft.dependencies.recipe.FcRecipeProvider
import org.bukkit.Bukkit
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.ShapelessRecipe

/**
 * Bukkit implementation of [FcRecipeProvider].
 */
class BukkitFcRecipeProvider : FcRecipeProvider {

    override fun getCraftingRecipes(): List<FcCraftingRecipe> {
        val serverRecipes = Bukkit.recipeIterator()
        val results = mutableListOf<FcCraftingRecipe>()

        serverRecipes.forEach { recipe ->
            when (recipe) {
                is ShapedRecipe ->
                    BukkitFcCraftingRecipe.Shaped(recipe)

                is ShapelessRecipe ->
                    BukkitFcCraftingRecipe.Shapeless(recipe)

                else -> null
            }?.let(results::add)
        }

        return results
    }
}
