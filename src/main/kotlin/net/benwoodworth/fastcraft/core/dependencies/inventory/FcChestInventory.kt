package net.benwoodworth.fastcraft.core.dependencies.inventory

/**
 * A Minecraft chest inventory.
 */
abstract class ChestInventoryAdapter(
        baseInventory: Any
) : InventoryAdapter(baseInventory), ItemGrid {

    /**
     * Add items to the inventory.
     *
     * @param items the items to add
     * @return items that could not be added to the inventory
     */
    abstract fun addItems(items: Collection<ItemAdapter>): List<ItemAdapter>

    /**
     * Remove items from the inventory.
     *
     * @param items the items to remove
     * @return items that could not be removed from the inventory
     */
    abstract fun removeItems(items: Collection<ItemAdapter>): List<ItemAdapter>
}
