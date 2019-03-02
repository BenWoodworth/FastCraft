package net.benwoodworth.fastcraft

import net.benwoodworth.fastcraft.crafting.CraftingGuiFactory
import net.benwoodworth.fastcraft.platform.server.FcPlayerJoinEvent
import net.benwoodworth.fastcraft.platform.server.FcServer
import net.benwoodworth.fastcraft.platform.server.FcTaskFactory
import javax.inject.Inject

class FastCraft @Inject internal constructor(
    serverListeners: FcServer,
    private val taskFactory: FcTaskFactory,
    private val craftingGuiFactory: CraftingGuiFactory
) {

    init {
        serverListeners.onPlayerJoin += ::onPlayerJoin
    }

    fun disable() {
    }

    private fun onPlayerJoin(event: FcPlayerJoinEvent) {
        with(taskFactory) {
            val task = createFcTask(delaySeconds = 20.0) {
                craftingGuiFactory.openFastCraftGui(event.player)
            }

            task.schedule()
        }
    }
}
