package net.benwoodworth.fastcraft.dependencies.recipe

import net.benwoodworth.fastcraft.dependencies.item.FcItem

/**
 * An ingredient to a recipe.
 */
interface FcIngredient {

    /**
     * Check if this ingredient matches an item.
     *
     * @param item the item to check.
     * @return `true` iff this ingredient matches the item.
     */
    fun matches(item: FcItem): Boolean
}
