package net.benwoodworth.fastcraft.implementations.bukkit.recipe

import net.benwoodworth.fastcraft.dependencies.recipe.CraftingRecipe
import net.benwoodworth.fastcraft.dependencies.recipe.RecipeProvider
import org.bukkit.Bukkit
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.ShapelessRecipe

/**
 * Bukkit implementation of [RecipeProvider].
 */
class BukkitRecipeProvider : RecipeProvider {

    override fun getCraftingRecipes(): List<CraftingRecipe> {
        val serverRecipes = Bukkit.recipeIterator()
        val results = mutableListOf<CraftingRecipe>()

        serverRecipes.forEach { recipe ->
            when (recipe) {
                is ShapedRecipe ->
                    BukkitCraftingRecipe.Shaped(recipe)

                is ShapelessRecipe ->
                    BukkitCraftingRecipe.Shapeless(recipe)

                else -> null
            }?.let(results::add)
        }

        return results
    }
}
