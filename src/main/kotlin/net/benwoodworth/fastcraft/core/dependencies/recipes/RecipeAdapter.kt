package net.benwoodworth.fastcraft.core.dependencies.recipes

import net.benwoodworth.fastcraft.core.dependencies.inventory.ItemAdapter
import net.benwoodworth.fastcraft.core.dependencies.permissions.PermissionAdapter

/**
 * Adapts a recipe from the native implementation.
 */
interface RecipeAdapter {

    /** The ingredients in the recipe. */
    val ingredients: List<ItemAdapter>

    /** The results of the recipe. */
    val results: List<ItemAdapter>

    /** The permissions required to craft the recipe. */
    val permissions: List<PermissionAdapter>

    /**
     * Prepare the recipe, allowing other plugins to change it before it is crafted.
     */
    //fun prepare(player: FcPlayer<TItem>): FcPrepareRecipeEvent<TItem>

    /**
     * Simulate the crafting of this item with this recipe.
     *
     * @param player the player crafting the recipe
     * @return the resulting crafting event
     */
    //fun craft(player: FcPlayer<TItem>): FcItemCraftEvent<TItem>

    /**
     * Check if this recipe is equal to an object.
     *
     * @param other the object to compare to
     * @return `true` if the recipe are equal
     */
    override fun equals(other: Any?): Boolean

    /**
     * Generates a hash code for this recipe.
     *
     * @return the hash code
     */
    override fun hashCode(): Int
}

