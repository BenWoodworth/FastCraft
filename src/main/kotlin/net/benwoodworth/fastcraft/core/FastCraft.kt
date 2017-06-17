package net.benwoodworth.fastcraft.core

import net.benwoodworth.fastcraft.core.dependencies.event.EventListenerRegistry
import javax.inject.Inject

/**
 * The core class of FastCraft.
 */
class FastCraft @Inject constructor(
        eventListeners: EventListenerRegistry
) {

    init {
        eventListeners.pluginEnable += this::onPluginEnable
        eventListeners.pluginDisable += this::onPluginDisable
        eventListeners.playerJoin += this::onPlayerJoin
    }

    fun onPluginEnable() {
        println("FastCraft core enabled")
    }

    fun onPluginDisable() {
        println("FastCraft core disabled")
    }

    fun onPlayerJoin() {
        //event.player.sendMessage("Welcome to the server!")
    }
}
