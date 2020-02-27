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
        Strings.load()
        playerEventsListeners.onPlayerOpenWorkbench += ::onPlayerOpenWorkbench
    }

    fun disable() {
    }

    private fun onPlayerOpenWorkbench(event: FcPlayerOpenWorkbenchEvent) {
        if (!event.player.hasPermission(Permissions.FASTCRAFT_USE)) {
            return
        }

        fastCraftGuiFactory
            .createFastCraftGui(event.player)
            .open()

        event.cancel()
    }
}
