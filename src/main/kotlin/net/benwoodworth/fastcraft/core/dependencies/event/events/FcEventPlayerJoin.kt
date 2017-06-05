package net.benwoodworth.fastcraft.core.dependencies.event.events

import net.benwoodworth.fastcraft.core.dependencies.event.FcEvent
import net.benwoodworth.fastcraft.core.dependencies.inventory.FcItem
import net.benwoodworth.fastcraft.core.dependencies.player.FcPlayer

/**
 * An event for players joining the server.
 *
 * @param TItem the item type
 */
interface FcEventPlayerJoin<TItem : FcItem<*>> : FcEvent {

    /** The player who joined. */
    val player: FcPlayer<TItem>
}
