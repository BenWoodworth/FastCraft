package net.benwoodworth.fastcraft.implementations.sponge.recipe

import net.benwoodworth.fastcraft.dependencies.recipe.CraftingRecipe
import net.benwoodworth.fastcraft.dependencies.recipe.RecipeProvider
import org.spongepowered.api.Sponge
import org.spongepowered.api.item.recipe.crafting.ShapedCraftingRecipe
import org.spongepowered.api.item.recipe.crafting.ShapelessCraftingRecipe

/**
 * Sponge implementation of [RecipeProvider].
 */
class SpongeRecipeProvider(
        private val plugin: Any
) : RecipeProvider {

    override fun getCraftingRecipes(): List<CraftingRecipe> {
        val serverRecipes = Sponge.getRegistry().craftingRecipeRegistry.recipes
        val results = mutableListOf<CraftingRecipe>()

        serverRecipes.forEach { recipe ->
            when (recipe) {
                is ShapedCraftingRecipe ->
                    SpongeCraftingRecipe.Shaped(recipe, plugin)

                is ShapelessCraftingRecipe ->
                    SpongeCraftingRecipe.Shapeless(recipe, plugin)

                else -> null
            }?.let(results::add)
        }

        return results
    }
}
