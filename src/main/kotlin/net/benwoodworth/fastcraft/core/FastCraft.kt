package net.benwoodworth.fastcraft.core

import net.benwoodworth.fastcraft.core.dependencies.event.EventListenerRegistry
import net.benwoodworth.fastcraft.core.dependencies.event.events.EventPlayerJoin
import net.benwoodworth.fastcraft.core.dependencies.event.events.EventPluginDisable
import net.benwoodworth.fastcraft.core.dependencies.event.events.EventPluginEnable
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

    fun onPluginEnable(event: EventPluginEnable) {
        println("FastCraft core enabled")
    }

    fun onPluginDisable(event: EventPluginDisable) {
        println("FastCraft core disabled")
    }

    fun onPlayerJoin(event: EventPlayerJoin<TItem>) {
        event.player.sendMessage("Welcome to the server!")
    }

}
