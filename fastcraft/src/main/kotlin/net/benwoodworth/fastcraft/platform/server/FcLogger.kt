package net.benwoodworth.fastcraft.platform.server

import net.benwoodworth.fastcraft.util.Extensible

/**
 * Logs plugin messages to the server console.
 */
interface FcLogger : Extensible {

    /**
     * Log an [error] to the console.
     */
    fun error(error: String)

    /**
     * Log [info] to the console.
     */
    fun info(info: String)

    /**
     * Log a [warning] to the console.
     */
    fun warn(warning: String)
}
