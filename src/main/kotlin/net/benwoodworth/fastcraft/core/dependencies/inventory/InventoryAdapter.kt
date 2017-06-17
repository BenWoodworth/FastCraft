package net.benwoodworth.fastcraft.core.dependencies.inventory

import net.benwoodworth.fastcraft.core.dependencies.util.Adapter
import net.benwoodworth.fastcraft.core.dependencies.player.PlayerAdapter

/**
 * An adapter for Minecraft inventories.
 */
abstract class InventoryAdapter(baseInventory: Any) : Adapter(baseInventory) {

    /** The title of the inventory. */
    abstract val title: String?

    /** The players viewing the inventory. */
    abstract val viewers: List<PlayerAdapter>

    /** The carrier of this inventory. */
    abstract val carrier: Any?
}
