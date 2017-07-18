package net.benwoodworth.fastcraft.dependencies.item.recipe

import net.benwoodworth.fastcraft.dependencies.item.Item
import net.benwoodworth.fastcraft.dependencies.text.Text

/**
 * An ingredient of a recipe.
 */
interface Ingredient {

    /**
     * Check if an item matches this ingredient.
     *
     * @return `true` if the item matches this ingredient
     */
    fun matchesItem(item: Item): Boolean
}
