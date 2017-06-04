package net.benwoodworth.fastcraft.core.dependencies.inventory

/**
 * A Minecraft chest inventory.
 *
 * @param TItem the item type
 */
interface FcChestInventory<TItem : FcItem<*>> : FcInventory<TItem>, ItemGrid<TItem> {

    /**
     * Add items to the inventory.
     *
     * @param items the items to add
     * @return items that could not be added to the inventory
     */
    fun addItems(items: Collection<TItem>): List<TItem>

    /**
     * Remove items from the inventory.
     *
     * @param items the items to remove
     * @return items that could not be removed from the inventory
     */
    fun removeItems(items: Collection<TItem>): List<TItem>
}
