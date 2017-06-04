package net.benwoodworth.fastcraft.core.dependencies.recipes

import net.benwoodworth.fastcraft.core.dependencies.inventory.FcItem

/**
 * A custom recipe that adds extra functionality to a base recipe.
 *
 * @param TItem the item type
 */
interface FcModifiedRecipe<TItem : FcItem<*>> : FcRecipe<TItem> {

    /** The base recipe, overridden by this recipe */
    val baseRecipe: FcRecipe<TItem>
}
