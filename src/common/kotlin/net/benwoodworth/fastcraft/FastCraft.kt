package net.benwoodworth.fastcraft

import net.benwoodworth.fastcraft.crafting.FastCraftGuiFactory
import net.benwoodworth.fastcraft.platform.player.FcPlayerEvents
import net.benwoodworth.fastcraft.platform.player.FcPlayerOpenWorkbenchEvent
import javax.inject.Inject

class FastCraft @Inject internal constructor(
    playerEventsListeners: FcPlayerEvents,
    private val fastCraftGuiFactory: FastCraftGuiFactory
) {
    init {
        playerEventsListeners.onPlayerOpenWorkbench += ::onPlayerOpenWorkbench
    }

    fun disable() {
    }

    private fun onPlayerOpenWorkbench(event: FcPlayerOpenWorkbenchEvent) {
        event.cancel()

        fastCraftGuiFactory
            .createFastCraftGui(event.player)
            .open()
    }
}
