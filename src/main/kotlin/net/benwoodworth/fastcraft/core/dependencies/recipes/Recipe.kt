package net.benwoodworth.fastcraft.core.dependencies.recipes

import net.benwoodworth.fastcraft.core.dependencies.item.Item
import net.benwoodworth.fastcraft.core.dependencies.permissions.Permissions

/**
 * A Minecraft crafting recipe.
 */
interface Recipe {

    /** The ingredients in the recipe. */
    val ingredients: List<Item>

    /** The results of the recipe. */
    val results: List<Item>

    /** The permissions required to craft the recipe. */
    val permissions: List<Permissions>

//    /**
//     * Prepare the recipe, allowing other plugins to change it before it is crafted.
//     */
//    fun prepare(player: FcPlayer<TItem>): FcPrepareRecipeEvent<TItem>

//    /**
//     * Simulate the crafting of this item with this recipe.
//     *
//     * @param player the player crafting the recipe
//     * @return the resulting crafting event
//     */
//    fun craft(player: FcPlayer<TItem>): FcItemCraftEvent<TItem>

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

