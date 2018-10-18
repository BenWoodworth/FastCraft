package net.benwoodworth.fastcraft.bukkit.server

import java.util.logging.Level
import java.util.logging.Logger

@Suppress("ClassName")
inline class `BukkitFcLogger$1_13_R0_1`(
    private val logger: Logger
) : net.benwoodworth.fastcraft.bukkit.server.BukkitFcLogger {

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
