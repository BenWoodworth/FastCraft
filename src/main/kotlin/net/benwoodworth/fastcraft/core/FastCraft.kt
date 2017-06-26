package net.benwoodworth.fastcraft.core

import net.benwoodworth.fastcraft.core.dependencies.event.EventPlayerJoin
import net.benwoodworth.fastcraft.core.dependencies.event.EventPluginDisable
import net.benwoodworth.fastcraft.core.dependencies.event.EventPluginEnable
import net.benwoodworth.fastcraft.core.dependencies.server.Logger
import net.benwoodworth.fastcraft.core.util.EventListener
import javax.inject.Inject

/**
 * The core class of FastCraft.
 */
class FastCraft @Inject constructor(
        listenerPluginEnable: EventListener<EventPluginEnable>,
        listenerPluginDisable: EventListener<EventPluginDisable>,
        listenerPlayerJoin: EventListener<EventPlayerJoin>,

        private val logger: Logger
) {

    init {
        listenerPluginEnable += this::onPluginEnable
        listenerPluginDisable += this::onPluginDisable
        listenerPlayerJoin += this::onPlayerJoin
    }

    fun onPluginEnable() {
        logger.logInfo("FastCraft core enabled")
    }

    fun onPluginDisable() {
        logger.logInfo("FastCraft core disabled")
    }

    fun onPlayerJoin() {
        //event.player.sendMessage("Welcome to the server!")
    }
}
