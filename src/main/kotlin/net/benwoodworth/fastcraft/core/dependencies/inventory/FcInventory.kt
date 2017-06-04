package net.benwoodworth.fastcraft.core.dependencies.inventory

import net.benwoodworth.fastcraft.core.dependencies.player.FcPlayer

/**
 * A Minecraft inventory.
 *
 * @param TItem type of item contained in this inventory.
 */
interface FcInventory<TItem : FcItem<*>> {

    /** The title of the inventory. */
    val title: String?

    /** The players viewing the inventory. */
    val viewers: List<FcPlayer<TItem>>

    /** The carrier of this inventory. */
    val carrier: Any?
}
