package net.benwoodworth.fastcraft.impl.sponge.item.recipe

import net.benwoodworth.fastcraft.dependencies.item.recipe.Recipe
import net.benwoodworth.fastcraft.dependencies.item.recipe.RecipeProvider
import org.spongepowered.api.Sponge

/**
 * Sponge implementation of [RecipeProvider].
 */
class SpongeRecipeProvider : RecipeProvider {

    override fun getServerRecipes(): List<Recipe> {
        return Sponge.getRegistry().craftingRecipeRegistry.recipes
                .map(::SpongeRecipe)
    }
}
