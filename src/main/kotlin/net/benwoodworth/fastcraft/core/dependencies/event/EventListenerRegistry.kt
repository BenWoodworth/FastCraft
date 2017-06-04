package net.benwoodworth.fastcraft.core.dependencies.event

import net.benwoodworth.fastcraft.core.dependencies.event.events.EventPlayerJoin
import net.benwoodworth.fastcraft.core.dependencies.event.events.EventPluginDisable
import net.benwoodworth.fastcraft.core.dependencies.event.events.EventPluginEnable
import net.benwoodworth.fastcraft.core.dependencies.inventory.FcItem

/**
 * A registry of event listeners.
 */
interface EventListenerRegistry<TItem : FcItem<*>> {

    val pluginEnable: EventListener<EventPluginEnable>
    val pluginDisable: EventListener<EventPluginDisable>
    val playerJoin: EventListener<EventPlayerJoin<TItem>>
}
