package net.benwoodworth.fastcraft

import net.benwoodworth.fastcraft.platform.server.FcLogger
import javax.inject.Inject

class FastCraft @Inject internal constructor(
    private val logger: FcLogger
) {

    init {
        logger.info("FASTCRAFT INITIALIZED")
    }

    fun enable() {
        logger.info("FASTCRAFT ENABLED")
    }

    fun disable() {
        logger.info("FASTCRAFT DISABLED")
    }
}
