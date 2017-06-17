package net.benwoodworth.fastcraft.core.dependencies.event

import net.benwoodworth.fastcraft.core.dependencies.event.events.PlayerJoinEvent
import net.benwoodworth.fastcraft.core.dependencies.event.events.PluginDisableEvent
import net.benwoodworth.fastcraft.core.dependencies.event.events.PluginEnableEvent

/**
 * A registry of event listeners.
 */
abstract class EventListenerRegistry {

    val pluginEnable = EventListener<PluginEnableEvent>()
    val pluginDisable = EventListener<PluginDisableEvent>()
    val playerJoin = EventListener<PlayerJoinEvent>()
}
