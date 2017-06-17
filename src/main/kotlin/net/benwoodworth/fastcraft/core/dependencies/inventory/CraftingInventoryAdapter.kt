package net.benwoodworth.fastcraft.core.dependencies.inventory

import net.benwoodworth.fastcraft.core.dependencies.Adapter
import net.benwoodworth.fastcraft.core.dependencies.recipes.RecipeAdapter

/**
 * A Minecraft crafting inventory.
 */
abstract class CraftingInventoryAdapter(
        baseInventory: Any
) : Adapter(baseInventory) {

    /** The crafting inventory's crafting matrix */
    abstract val matrix: ItemGrid

    /** The item in the result slot of the crafting inventory */
    abstract val result: ItemAdapter

    /** The current recipe in the crafting inventory */
    abstract val recipe: RecipeAdapter
}
