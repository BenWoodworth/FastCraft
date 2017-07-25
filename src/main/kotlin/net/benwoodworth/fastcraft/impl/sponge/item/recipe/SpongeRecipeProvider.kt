package net.benwoodworth.fastcraft.impl.sponge.item.recipe

import net.benwoodworth.fastcraft.dependencies.item.recipe.Recipe
import net.benwoodworth.fastcraft.dependencies.item.recipe.RecipeProvider
import org.spongepowered.api.Sponge
import org.spongepowered.api.item.recipe.crafting.ShapedCraftingRecipe
import org.spongepowered.api.item.recipe.crafting.ShapelessCraftingRecipe

/**
 * Sponge implementation of [RecipeProvider].
 */
class SpongeRecipeProvider : RecipeProvider {

    override fun getServerRecipes(): List<Recipe> {
        val serverRecipes = Sponge.getRegistry().craftingRecipeRegistry.recipes
        val results = mutableListOf<Recipe>()

        results.addAll(
            serverRecipes
                    .filterIsInstance<ShapedCraftingRecipe>()
                    .map(SpongeRecipe::Shaped)
        )

        results.addAll(
                serverRecipes
                        .filterIsInstance<ShapelessCraftingRecipe>()
                        .map(SpongeRecipe::Shapeless)
        )

        return results
    }
}
