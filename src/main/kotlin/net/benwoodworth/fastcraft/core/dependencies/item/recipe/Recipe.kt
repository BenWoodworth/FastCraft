package net.benwoodworth.fastcraft.core.dependencies.item.recipe

import net.benwoodworth.fastcraft.core.dependencies.item.Item
import net.benwoodworth.fastcraft.core.dependencies.item.ItemGrid
import net.benwoodworth.fastcraft.core.dependencies.player.Player

/**
 * A Minecraft crafting recipe.
 */
interface Recipe {

    /** The ingredients in the recipe, and their amounts. */
    val ingredients: Map<Ingredient, Int>

    /** The base result of the recipe, which may later be customized. */
    val baseResult: Item

    /**
     * Get the results of this recipe.
     *
     * @param itemGrid the grid of items used to craft the recipe
     * @param player the player crafting the recipe
     * @return the results of the recipe, or an empty list if invalid
     */
    fun getResults(itemGrid: ItemGrid, player: Player): List<Item>

    /**
     * Simulate the crafting of this item with this recipe.
     *
     * @param player the player crafting the recipe
     * @return the resulting crafting event
     */
    fun craft(player: Player)

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

