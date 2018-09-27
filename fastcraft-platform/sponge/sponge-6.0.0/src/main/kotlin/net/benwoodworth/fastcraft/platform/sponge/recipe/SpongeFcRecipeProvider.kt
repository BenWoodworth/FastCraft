package net.benwoodworth.fastcraft.platform.sponge.recipe

import net.benwoodworth.fastcraft.platform.api.recipe.FcCraftingRecipe
import net.benwoodworth.fastcraft.platform.api.recipe.FcRecipeProvider
import org.spongepowered.api.Sponge
import org.spongepowered.api.item.recipe.crafting.ShapedCraftingRecipe
import org.spongepowered.api.item.recipe.crafting.ShapelessCraftingRecipe

/**
 * Sponge implementation of [FcRecipeProvider].
 */
class SpongeFcRecipeProvider(
        private val plugin: Any
) : FcRecipeProvider {

    override fun getCraftingRecipes(): List<FcCraftingRecipe> {
        val serverRecipes = Sponge.getRegistry().craftingRecipeRegistry.recipes
        val results = mutableListOf<FcCraftingRecipe>()

        serverRecipes.forEach { recipe ->
            when (recipe) {
                is ShapedCraftingRecipe ->
                    SpongeFcCraftingRecipe.Shaped(recipe, plugin)

                is ShapelessCraftingRecipe ->
                    SpongeFcCraftingRecipe.Shapeless(recipe, plugin)

                else -> null
            }?.let(results::add)
        }

        return results
    }
}
