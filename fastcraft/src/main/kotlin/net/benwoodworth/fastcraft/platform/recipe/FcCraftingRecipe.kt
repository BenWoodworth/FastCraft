package net.benwoodworth.fastcraft.platform.recipe

/**
 * A Minecraft crafting recipe.
 */
interface FcCraftingRecipe {

    /**
     * An ID unique to this recipe. `namespace:id`
     */
    val id: String

    /**
     * The ingredients required to craft this recipe.
     */
    val ingredients: List<FcIngredient>
}

