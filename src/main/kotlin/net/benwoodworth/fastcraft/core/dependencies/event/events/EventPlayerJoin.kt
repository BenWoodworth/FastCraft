package net.benwoodworth.fastcraft.core.dependencies.event.events

import net.benwoodworth.fastcraft.core.dependencies.event.Event
import net.benwoodworth.fastcraft.core.dependencies.inventory.FcItem
import net.benwoodworth.fastcraft.core.dependencies.player.FcPlayer

/**
 * An event for players joining the server.
 *
 * @param TFcItem the item type
 */
interface EventPlayerJoin<TFcItem : FcItem<*>> : Event {

    /** The player who joined. */
    val player: FcPlayer<TFcItem>
}
