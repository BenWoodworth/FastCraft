package net.benwoodworth.fastcraft.core.dependencies.item

import net.benwoodworth.fastcraft.core.dependencies.item.recipe.RecipeProvider

/**
 * Dagger module for item dependencies.
 */
interface ModuleItem {

    fun itemBuilder(): ItemBuilder

    fun recipeProvider(): RecipeProvider
}
