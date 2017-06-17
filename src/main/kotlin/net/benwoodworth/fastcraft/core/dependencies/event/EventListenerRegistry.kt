package net.benwoodworth.fastcraft.core.dependencies.event

import net.benwoodworth.fastcraft.core.dependencies.event.events.PlayerJoinEventAdapter
import net.benwoodworth.fastcraft.core.dependencies.event.events.PluginDisableEventAdapter
import net.benwoodworth.fastcraft.core.dependencies.event.events.PluginEnableEventAdapter

/**
 * A registry of event listeners.
 */
abstract class EventListenerRegistry {

    val pluginEnable = EventListener<PluginEnableEventAdapter>()
    val pluginDisable = EventListener<PluginDisableEventAdapter>()
    val playerJoin = EventListener<PlayerJoinEventAdapter>()
}
