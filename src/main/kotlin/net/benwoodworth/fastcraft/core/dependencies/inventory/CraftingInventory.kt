package net.benwoodworth.fastcraft.core.dependencies.inventory

import net.benwoodworth.fastcraft.core.dependencies.recipes.Recipe

/**
 * A Minecraft crafting inventory.
 */
interface CraftingInventory {

    /** The crafting inventory's crafting matrix */
    val matrix: ItemGrid

    /** The item in the result slot of the crafting inventory */
    val result: Item

    /** The current recipe in the crafting inventory */
    val recipe: Recipe
}
