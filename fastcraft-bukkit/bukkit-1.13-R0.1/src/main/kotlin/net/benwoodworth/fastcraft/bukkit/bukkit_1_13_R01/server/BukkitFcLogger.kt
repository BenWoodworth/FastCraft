package net.benwoodworth.fastcraft.bukkit.bukkit_1_13_R01.server

import java.util.logging.Level
import java.util.logging.Logger

class BukkitFcLogger(
    private val logger: Logger
) : BukkitFcLogger {

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
