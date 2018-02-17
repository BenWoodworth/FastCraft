package net.benwoodworth.fastcraft.dependencies.recipe

import net.benwoodworth.fastcraft.dependencies.api.item.Item

/**
 * An ingredient to a recipe.
 */
interface Ingredient {

    /**
     * Check if this ingredient matches an item.
     *
     * @param item the item to check
     * @return true iff this ingredient matches the item
     */
    fun matches(item: Item): Boolean
}
