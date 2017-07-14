package net.benwoodworth.fastcraft.dependencies.item

import net.benwoodworth.fastcraft.dependencies.item.recipe.RecipeProvider

/**
 * Dagger module for item dependencies.
 */
interface ModuleItem {

    fun itemBuilder(): ItemBuilder

    fun recipeProvider(): RecipeProvider
}
