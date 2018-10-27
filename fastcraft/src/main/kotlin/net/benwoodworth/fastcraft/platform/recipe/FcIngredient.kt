package net.benwoodworth.fastcraft.platform.recipe

import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.util.Extensible

/**
 * An ingredient to a recipe.
 */
interface FcIngredient : Extensible {

    /**
     * Check if this ingredient matches an item.
     *
     * @param item the item to check.
     * @return `true` iff this ingredient matches the item.
     */
    fun matches(item: FcItem): Boolean
}
