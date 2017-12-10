package net.benwoodworth.fastcraft.dependencies.event

/**
 * Dagger module for event dependencies.
 */
interface ModuleEvent {

    fun listenerPlayerJoin(): Listener<EventPlayerJoin>

    fun listenerPluginDisable(): Listener<EventPluginDisable>

    fun listenerPluginEnable(): Listener<EventPluginEnable>
}
