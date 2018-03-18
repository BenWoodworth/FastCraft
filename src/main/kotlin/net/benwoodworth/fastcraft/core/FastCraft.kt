package net.benwoodworth.fastcraft.core

import net.benwoodworth.fastcraft.core.gui.crafting.CraftingGuiPresenterFactory
import net.benwoodworth.fastcraft.dependencies.event.FcListener
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
        private val listenerPluginEnable: FcListener<FcEventPluginEnable>,
        private val listenerPluginDisable: FcListener<FcEventPluginDisable>,
        private val listenerPlayerJoin: FcListener<FcEventPlayerJoin>,

        private val taskBuilder: Provider<FcTaskBuilder>,
        private val craftingGuiPresenterFactory: CraftingGuiPresenterFactory
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

    private fun onPlayerJoin(event: FcEventPlayerJoin) {
        // TODO Remove debug code
        taskBuilder.get().delay(1).execute {
            craftingGuiPresenterFactory
                    .create(event.player)
                    .open()
        }
    }
}
