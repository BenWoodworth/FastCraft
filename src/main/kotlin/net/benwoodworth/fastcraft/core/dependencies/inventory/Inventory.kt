package net.benwoodworth.fastcraft.core.dependencies.inventory

import net.benwoodworth.fastcraft.core.dependencies.player.Player
import net.benwoodworth.fastcraft.core.dependencies.text.Text

/**
 * A Minecraft inventory.
 */
interface Inventory {

    /** The title of the inventory. */
    val title: Text?

    /** The players viewing the inventory. */
    val viewers: List<Player>

    /** The carrier of this inventory. */
    val carrier: Any?
}
