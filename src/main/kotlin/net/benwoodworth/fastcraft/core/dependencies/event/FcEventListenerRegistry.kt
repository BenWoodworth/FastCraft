package net.benwoodworth.fastcraft.core.dependencies.event

import net.benwoodworth.fastcraft.core.dependencies.event.events.FcEventPlayerJoin
import net.benwoodworth.fastcraft.core.dependencies.event.events.FcEventPluginDisable
import net.benwoodworth.fastcraft.core.dependencies.event.events.FcEventPluginEnable

/**
 * A registry of event listeners.
 */
abstract class FcEventListenerRegistry {

    val pluginEnable = FcEventListener<FcEventPluginEnable>()
    val pluginDisable = FcEventListener<FcEventPluginDisable>()
    val playerJoin = FcEventListener<FcEventPlayerJoin>()
}
