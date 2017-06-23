package net.benwoodworth.fastcraft.core.dependencies.item.inventory

import org.bukkit.entity.Item

/**
 * A Minecraft chest inventory.
 */
interface ChestInventory : net.benwoodworth.fastcraft.core.dependencies.item.ItemGrid {

    /**
     * Add items to the inventory.
     *
     * @param items the items to add
     * @return items that could not be added to the inventory
     */
    fun addItems(items: Collection<Item>): List<Item>

    /**
     * Remove items from the inventory.
     *
     * @param items the items to remove
     * @return items that could not be removed from the inventory
     */
    fun removeItems(items: Collection<Item>): List<Item>
}
