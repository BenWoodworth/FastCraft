package net.benwoodworth.fastcraft.core

import net.benwoodworth.fastcraft.core.dependencies.event.FcEventListenerRegistry
import net.benwoodworth.fastcraft.core.dependencies.event.events.FcEventPlayerJoin
import net.benwoodworth.fastcraft.core.dependencies.event.events.FcEventPluginDisable
import net.benwoodworth.fastcraft.core.dependencies.event.events.FcEventPluginEnable
import javax.inject.Inject

/**
 * The core class of FastCraft.
 */
class FastCraft @Inject constructor(
        eventListeners: FcEventListenerRegistry
) {

    init {
        eventListeners.pluginEnable += this::onPluginEnable
        eventListeners.pluginDisable += this::onPluginDisable
        eventListeners.playerJoin += this::onPlayerJoin
    }

    fun onPluginEnable(event: FcEventPluginEnable) {
        println("FastCraft core enabled")
    }

    fun onPluginDisable(event: FcEventPluginDisable) {
        println("FastCraft core disabled")
    }

    fun onPlayerJoin(event: FcEventPlayerJoin) {
        event.player.sendMessage("Welcome to the server!")
    }

}
