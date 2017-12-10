package net.benwoodworth.fastcraft.impl.bukkit.server

import net.benwoodworth.fastcraft.dependencies.server.Logger
import net.benwoodworth.fastcraft.util.Adapter
import java.util.logging.Level
import java.util.logging.Logger as Bukkit_Logger

/**
 * Bukkit implementation of [Logger].
 */
class BukkitLogger(
        logger: Bukkit_Logger
) : Logger, Adapter<Bukkit_Logger>(logger) {

    override fun error(message: String) = base.log(Level.SEVERE, message)

    override fun info(message: String) = base.log(Level.INFO, message)

    override fun warn(message: String) = base.log(Level.WARNING, message)
}
