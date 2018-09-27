package net.benwoodworth.fastcraft.platform.api.recipe

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

    /**
     * Check if this recipe is equal to an object.
     *
     * @param other the object to compare to.
     * @return `true` iff the recipes are equal.
     */
    override fun equals(other: Any?): Boolean

    /**
     * Generates a hash code for this recipe.
     *
     * @return the hash code.
     */
    override fun hashCode(): Int
}

