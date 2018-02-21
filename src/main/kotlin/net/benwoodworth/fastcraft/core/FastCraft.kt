package net.benwoodworth.fastcraft.core

import net.benwoodworth.fastcraft.dependencies.api.Listener
import net.benwoodworth.fastcraft.dependencies.event.FcEventPlayerJoin
import net.benwoodworth.fastcraft.dependencies.event.FcEventPluginDisable
import net.benwoodworth.fastcraft.dependencies.event.FcEventPluginEnable
import net.benwoodworth.fastcraft.dependencies.server.FcTaskBuilder
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * The core class of FastCraft.
 */
@Singleton
class FastCraft @Inject constructor(
        listenerPluginEnable: Listener<FcEventPluginEnable>,
        listenerPluginDisable: Listener<FcEventPluginDisable>,
        listenerPlayerJoin: Listener<FcEventPlayerJoin>,

        private val taskBuilder: Provider<FcTaskBuilder>
        //private val guiCraftingFactory: GuiCraftingFactory
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

    private fun onPlayerJoin(/*event: FcEventPlayerJoin*/) {
        // TODO Remove debug code
//        taskBuilder.get()
//                .delay(1).execute {
//            guiCraftingFactory.create(event.player).open()
//        }
    }
}
