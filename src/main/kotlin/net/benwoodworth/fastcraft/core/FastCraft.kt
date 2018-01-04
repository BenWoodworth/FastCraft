package net.benwoodworth.fastcraft.core

import net.benwoodworth.fastcraft.dependencies.event.EventPlayerJoin
import net.benwoodworth.fastcraft.dependencies.event.EventPluginDisable
import net.benwoodworth.fastcraft.dependencies.event.EventPluginEnable
import net.benwoodworth.fastcraft.dependencies.event.Listener
import net.benwoodworth.fastcraft.dependencies.server.TaskBuilder
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * The core class of FastCraft.
 */
@Singleton
class FastCraft @Inject constructor(
        listenerPluginEnable: Listener<EventPluginEnable>,
        listenerPluginDisable: Listener<EventPluginDisable>,
        listenerPlayerJoin: Listener<EventPlayerJoin>,

        private val taskBuilder: Provider<TaskBuilder>
        //private val fastCraftGui: FastCraftGui.Factory
) {

    init {
        listenerPluginEnable += this::onPluginEnable
        listenerPluginDisable += this::onPluginDisable
        listenerPlayerJoin += this::onPlayerJoin
    }

    private fun onPluginEnable() {
        println("FASTCRAFT ENABLED")
    }

    private fun onPluginDisable() {
        println("FASTCRAFT DISABLED")
    }

    private fun onPlayerJoin(event: EventPlayerJoin) {
        // TODO Remove debug code
//        taskBuilder.get()
//                .delay(1).execute {
//            fastCraftGui.create(event.player).open()
//        }
    }
}
