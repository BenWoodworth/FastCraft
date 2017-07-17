package net.benwoodworth.fastcraft.dependencies.item

import net.benwoodworth.fastcraft.dependencies.item.recipe.RecipeProvider

/**
 * Dagger module for item dependencies.
 */
interface ModuleItem {

    fun itemBuilder(): Item.Builder

    fun recipeProvider(): RecipeProvider
}
