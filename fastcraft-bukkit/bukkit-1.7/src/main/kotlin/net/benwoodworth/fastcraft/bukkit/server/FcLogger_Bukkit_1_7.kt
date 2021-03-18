package net.benwoodworth.fastcraft.bukkit.server

import java.util.logging.Level
import java.util.logging.Logger
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FcLogger_Bukkit_1_7 @Inject constructor(
    private val logger: Logger,
) : FcLogger_Bukkit {
    override fun error(error: String) {
        logger.log(Level.SEVERE, error)
    }

    override fun info(info: String) {
        logger.log(Level.INFO, info)
    }

    override fun warn(warning: String) {
        logger.log(Level.WARNING, warning)
    }
}
