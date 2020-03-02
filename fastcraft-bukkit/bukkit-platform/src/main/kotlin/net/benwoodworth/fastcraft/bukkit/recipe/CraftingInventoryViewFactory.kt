package net.benwoodworth.fastcraft.bukkit.recipe

import org.bukkit.entity.Player
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.Recipe

interface CraftingInventoryViewFactory {
    fun create(
        player: Player,
        inventoryHolder: InventoryHolder?,
        recipe: Recipe?
    ): InventoryView
}