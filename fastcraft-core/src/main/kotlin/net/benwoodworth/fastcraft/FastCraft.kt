package net.benwoodworth.fastcraft

import net.benwoodworth.fastcraft.api.FastCraftApi
import net.benwoodworth.fastcraft.api.FastCraftPreferences
import net.benwoodworth.fastcraft.crafting.FastCraftGui
import net.benwoodworth.fastcraft.events.HandlerSet
import net.benwoodworth.fastcraft.platform.player.FcOpenCraftingTableNaturallyEvent
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.player.FcPlayerEvents
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FastCraft @Inject internal constructor(
    fcPlayerEvents: FcPlayerEvents,
    private val fastCraftGuiFactory: FastCraftGui.Factory,
    private val fastCraftPreferences: FastCraftPreferences,
    fastCraftCommand: FastCraftCommand,
    private val permissions: Permissions,
    private val config: FastCraftConfig,
    fcPlayerOperations: FcPlayer.Operations,
    api: FastCraftApi,
    private val disableHandlers: DisableHandlers,
) : FcPlayer.Operations by fcPlayerOperations {
    val onDisable: HandlerSet<Unit> = HandlerSet()

    init {
        @Suppress("DEPRECATION")
        FastCraftApi.api = api

        Strings.load()
        fcPlayerEvents.onOpenCraftingTableNaturally += ::onPlayerOpenWorkbench
        fastCraftCommand.register()
    }

    fun disable() {
        disableHandlers.handlers.notifyHandlers(Unit)
    }

    fun reloadConfig() {
        config.load()
    }

    private fun onPlayerOpenWorkbench(event: FcOpenCraftingTableNaturallyEvent) {
        if (event.player.hasPermission(permissions.FASTCRAFT_USE) &&
            fastCraftPreferences.getEnabledOrDefault(event.player.uuid)
        ) {
            event.cancel()

            fastCraftGuiFactory
                .createFastCraftGui(event.player)
                .open()
        }
    }

    @Singleton
    class DisableHandlers @Inject constructor() {
        val handlers: HandlerSet<Unit> = HandlerSet()
    }
}
