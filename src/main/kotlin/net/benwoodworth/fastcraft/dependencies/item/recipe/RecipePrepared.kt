package net.benwoodworth.fastcraft.dependencies.item.recipe

import net.benwoodworth.fastcraft.dependencies.item.Item
import net.benwoodworth.fastcraft.dependencies.player.Player
import net.benwoodworth.fastcraft.util.Memento

/**
 * A recipe with specific ingredients and results.
 */
interface RecipePrepared {

    /**
     * The ingredients required to craft this recipe,
     * and the amount of the ingredients required.
     */
    val ingredients: Map<Ingredient, Int>

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