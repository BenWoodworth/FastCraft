package net.benwoodworth.fastcraft

import net.benwoodworth.fastcraft.crafting.FastCraftGui
import net.benwoodworth.fastcraft.data.PlayerSettings
import net.benwoodworth.fastcraft.platform.player.FcOpenCraftingTableNaturallyEvent
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.player.FcPlayerEvents
import javax.inject.Inject

class FastCraft @Inject internal constructor(
    fcPlayerEvents: FcPlayerEvents,
    private val fastCraftGuiFactory: FastCraftGui.Factory,
    private val playerPrefs: PlayerSettings,
    fastCraftCommand: FastCraftCommand,
    private val permissions: Permissions,
    private val config: FastCraftConfig,
    private val fcPlayerOperations: FcPlayer.Operations,
) {
    init {
        Strings.load()
        fcPlayerEvents.onOpenCraftingTableNaturally += ::onPlayerOpenWorkbench
        fastCraftCommand.register()
    }

    fun disable() {
        playerPrefs.close()
    }

    fun reloadConfig() {
        config.load()
    }

    private fun onPlayerOpenWorkbench(event: FcOpenCraftingTableNaturallyEvent) {
        if (fcPlayerOperations.run { event.player.hasPermission(permissions.FASTCRAFT_USE) } &&
            playerPrefs.getFastCraftEnabled(event.player)
        ) {
            event.cancel()

            fastCraftGuiFactory
                .createFastCraftGui(event.player)
                .open()
        }
    }
}
