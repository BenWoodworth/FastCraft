package net.benwoodworth.fastcraft.core.dependencies.event

import net.benwoodworth.fastcraft.core.dependencies.event.events.EventPlayerJoin
import net.benwoodworth.fastcraft.core.dependencies.event.events.EventPluginDisable
import net.benwoodworth.fastcraft.core.dependencies.event.events.EventPluginEnable

/**
 * A registry of event listeners.
 */
abstract class EventListenerRegistry {

    val pluginEnable = EventListener<EventPluginEnable>()
    val pluginDisable = EventListener<EventPluginDisable>()
    val playerJoin = EventListener<EventPlayerJoin>()
}
