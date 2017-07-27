package net.benwoodworth.fastcraft.core

import net.benwoodworth.fastcraft.dependencies.event.EventPlayerJoin
import net.benwoodworth.fastcraft.dependencies.event.EventPluginDisable
import net.benwoodworth.fastcraft.dependencies.event.EventPluginEnable
import net.benwoodworth.fastcraft.dependencies.event.Listener
import net.benwoodworth.fastcraft.dependencies.text.Text
import javax.inject.Inject
import javax.inject.Provider

/**
 * The core class of FastCraft.
 */
class FastCraft @Inject constructor(
        listenerPluginEnable: Listener<EventPluginEnable>,
        listenerPluginDisable: Listener<EventPluginDisable>,
        listenerPlayerJoin: Listener<EventPlayerJoin>,

        private val textBuilderProvider: Provider<Text.Builder>
) {

    init {
        listenerPluginEnable += this::onPluginEnable
        listenerPluginDisable += this::onPluginDisable
        listenerPlayerJoin += this::onPlayerJoin
    }

    fun onPluginEnable() {
        //logger.info("FastCraft core enabled")
    }

    fun onPluginDisable() {
        //logger.info("FastCraft core disabled")
    }

    fun onPlayerJoin(event: EventPlayerJoin) {
        event.player.sendMessage(textBuilderProvider.get()
                .text("FastCraft is up and running!")
                .build()
        )
    }
}
