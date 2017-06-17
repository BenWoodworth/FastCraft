package net.benwoodworth.fastcraft.core.dependencies.recipes

/**
 * A custom recipe that adds extra functionality to a base recipe.
 */
interface ModifiedRecipe : Recipe {

    /** The base recipe, overridden by this recipe */
    val overriddenRecipe: Recipe
}
