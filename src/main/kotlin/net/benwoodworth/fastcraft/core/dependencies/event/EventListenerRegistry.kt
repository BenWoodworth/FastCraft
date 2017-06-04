package net.benwoodworth.fastcraft.core.dependencies.event

import net.benwoodworth.fastcraft.core.dependencies.event.events.EventPlayerJoin
import net.benwoodworth.fastcraft.core.dependencies.event.events.EventServerStart
import net.benwoodworth.fastcraft.core.dependencies.inventory.FcItem

/**
 * A registry of event listeners.
 */
interface EventListenerRegistry<TItem : FcItem<*>> {

    val serverStart: EventListener<EventServerStart>

    val playerJoin: EventListener<EventPlayerJoin<TItem>>
}