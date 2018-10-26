package net.benwoodworth.fastcraft

import net.benwoodworth.fastcraft.gui.crafting.CraftingGuiPresenterFactory
import net.benwoodworth.fastcraft.platform.player.FcPlayerJoinEvent
import net.benwoodworth.fastcraft.platform.server.FcLogger
import net.benwoodworth.fastcraft.platform.server.FcServer
import net.benwoodworth.fastcraft.platform.server.FcTaskBuilder
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * The core class of FastCraft.
 */
@Singleton
class FastCraft @Inject constructor(
    private val taskBuilder: Provider<FcTaskBuilder>,
    private val craftingGuiPresenterFactory: CraftingGuiPresenterFactory,
    private val logger: FcLogger,
    private val server: FcServer
) {

    init {
        server.onPlayerJoin(::onPlayerJoin)
    }

    fun enable() {
        logger.info("FASTCRAFT ENABLED")
    }

    fun disable() {
        logger.info("FASTCRAFT DISABLED")
    }

    private fun onPlayerJoin(event: FcPlayerJoinEvent) {
        // TODO Remove debug code
        taskBuilder.get().delay(1).execute {
            craftingGuiPresenterFactory
                .create(event.player)
                .open()
        }
    }
}
