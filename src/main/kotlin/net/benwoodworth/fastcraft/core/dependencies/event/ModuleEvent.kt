package net.benwoodworth.fastcraft.core.dependencies.event

import net.benwoodworth.fastcraft.util.EventListener

/**
 * Dagger module for event dependencies.
 */
interface ModuleEvent {

    fun listenerPlayerJoin(): EventListener<EventPlayerJoin>

    fun listenerPluginDisable(): EventListener<EventPluginDisable>

    fun listenerPluginEnable(): EventListener<EventPluginEnable>
}
