package net.benwoodworth.fastcraft

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
    private val logger: FcLogger,
    private val server: FcServer
) {

    fun enable() {
        logger.info("FASTCRAFT ENABLED")
    }

    fun disable() {
        logger.info("FASTCRAFT DISABLED")
    }
}
