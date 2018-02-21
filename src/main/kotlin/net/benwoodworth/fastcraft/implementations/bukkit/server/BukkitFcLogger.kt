package net.benwoodworth.fastcraft.implementations.bukkit.server

import net.benwoodworth.fastcraft.dependencies.server.FcLogger
import net.benwoodworth.fastcraft.util.Adapter
import java.util.logging.Level
import java.util.logging.Logger as Bukkit_Logger

/**
 * Bukkit implementation of [FcLogger].
 */
class BukkitFcLogger(
        logger: Bukkit_Logger
) : FcLogger, Adapter<Bukkit_Logger>(logger) {

    override fun error(message: String) = base.log(Level.SEVERE, message)

    override fun info(message: String) = base.log(Level.INFO, message)

    override fun warn(message: String) = base.log(Level.WARNING, message)
}
