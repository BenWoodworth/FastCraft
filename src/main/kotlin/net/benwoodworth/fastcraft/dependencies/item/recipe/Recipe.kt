package net.benwoodworth.fastcraft.dependencies.item.recipe

import net.benwoodworth.fastcraft.dependencies.item.Item
import net.benwoodworth.fastcraft.dependencies.player.Player
import net.benwoodworth.fastcraft.util.Memento

/**
 * A Minecraft crafting recipe.
 */
interface Recipe {

    /**
     * Get prepared recipes that are possible to craft with
     * the given items.
     *
     * @param items the items available to craft with
     * @return a list of possible recipes
     */
    fun prepare(player: Player, vararg items: Item): List<Prepared>

    /**
     * Check if this recipe is equal to an object.
     *
     * @param other the object to compare to
     * @return `true` iff the recipe are equal
     */
    override fun equals(other: Any?): Boolean

    /**
     * Generates a hash code for this recipe.
     *
     * @return the hash code
     */
    override fun hashCode(): Int

    /**
     * A recipe with specific ingredients and results.
     */
    interface Prepared {

        /**
         * The items required to craft this recipe,
         * and the amount of the ingredients required.
         */
        val ingredients: List<Memento<Item>>

        /**
         * The results of the recipe.
         */
        val results: List<Memento<Item>>

        /**
         * Simulate the crafting of this recipe.
         *
         * @param player the player crafting the recipe
         * @return the resulting items, or null if unable to craft
         */
        fun craft(player: Player): List<Item>?
    }
}

