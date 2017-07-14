package net.benwoodworth.fastcraft.dependencies.item.recipe

import net.benwoodworth.fastcraft.dependencies.item.Item
import net.benwoodworth.fastcraft.dependencies.text.Text

/**
 * An ingredient of a recipe.
 */
interface Ingredient {

    /** The name of this ingredient */
    val name: Text

    /**
     * Check if an item matches this ingredient.
     *
     * @return `true` if the item matches
     */
    fun matchesItem(item: Item): Boolean
}
