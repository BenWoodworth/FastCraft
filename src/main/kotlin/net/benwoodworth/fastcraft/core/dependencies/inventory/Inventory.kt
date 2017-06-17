package net.benwoodworth.fastcraft.core.dependencies.inventory

import net.benwoodworth.fastcraft.core.dependencies.player.Player

/**
 * A Minecraft inventory.
 */
interface Inventory {

    /** The title of the inventory. */
    val title: String?

    /** The players viewing the inventory. */
    val viewers: List<Player>

    /** The carrier of this inventory. */
    val carrier: Any?
}
