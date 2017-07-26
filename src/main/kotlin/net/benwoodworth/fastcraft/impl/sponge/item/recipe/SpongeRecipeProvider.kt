package net.benwoodworth.fastcraft.impl.sponge.item.recipe

import net.benwoodworth.fastcraft.dependencies.item.recipe.CraftingRecipe
import net.benwoodworth.fastcraft.dependencies.item.recipe.RecipeProvider
import org.spongepowered.api.Sponge
import org.spongepowered.api.item.recipe.crafting.ShapedCraftingRecipe
import org.spongepowered.api.item.recipe.crafting.ShapelessCraftingRecipe

/**
 * Sponge implementation of [RecipeProvider].
 */
class SpongeRecipeProvider : RecipeProvider {

    override fun getCraftingRecipes(): List<CraftingRecipe> {
        val serverRecipes = Sponge.getRegistry().craftingRecipeRegistry.recipes
        val results = mutableListOf<CraftingRecipe>()

        results.addAll(
            serverRecipes
                    .filterIsInstance<ShapedCraftingRecipe>()
                    .map(SpongeCraftingRecipe::Shaped)
        )

        results.addAll(
                serverRecipes
                        .filterIsInstance<ShapelessCraftingRecipe>()
                        .map(SpongeCraftingRecipe::Shapeless)
        )

        return results
    }
}
