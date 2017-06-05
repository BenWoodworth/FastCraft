package net.benwoodworth.fastcraft.core

import net.benwoodworth.fastcraft.core.dependencies.event.EventListenerRegistry
import net.benwoodworth.fastcraft.core.dependencies.event.events.FcEventPlayerJoin
import net.benwoodworth.fastcraft.core.dependencies.event.events.FcEventPluginDisable
import net.benwoodworth.fastcraft.core.dependencies.event.events.FcEventPluginEnable
import net.benwoodworth.fastcraft.core.dependencies.inventory.FcItem
import javax.inject.Inject

/**
 * The core class of FastCraft.
 *
 * @param TItem The item type
 */
class FastCraft<TItem : FcItem<*>> @Inject constructor(
        eventListeners: EventListenerRegistry<TItem>
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

    fun onPlayerJoin(event: FcEventPlayerJoin<TItem>) {
        event.player.sendMessage("Welcome to the server!")
    }

}
