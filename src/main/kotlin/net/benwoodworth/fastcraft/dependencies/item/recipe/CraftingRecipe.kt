package net.benwoodworth.fastcraft.dependencies.item.recipe

import net.benwoodworth.fastcraft.dependencies.item.Item
import net.benwoodworth.fastcraft.dependencies.player.Player
import net.benwoodworth.fastcraft.util.Grid

/**
 * A Minecraft crafting recipe.
 */
interface CraftingRecipe {

    /**
     * The ingredients required to craft this recipe.
     */
    val ingredients: Grid<Ingredient>

    /**
     * Get the preview results, before actually crafting the recipe.
     *
     * @param player the player preparing the recipe
     * @param items the grid of items used to prepare the recipe
     * @return the resulting items, or empty if unable to prepare
     */
    fun prepare(player: Player, items: Grid<Item>): List<Item>

    /**
     * Simulate the crafting of the recipe.
     *
     * @param player the player preparing the recipe
     * @param items the grid of items used to craft the recipe
     * @return the resulting items, or empty if unable to craft
     */
    fun craft(player: Player, items: Grid<Item>): List<Item>

    /**
     * Check if this recipe is equal to an object.
     *
     * @param other the object to compare to
     * @return `true` iff the recipes are equal
     */
    override fun equals(other: Any?): Boolean

    /**
     * Generates a hash code for this recipe.
     *
     * @return the hash code
     */
    override fun hashCode(): Int
}

