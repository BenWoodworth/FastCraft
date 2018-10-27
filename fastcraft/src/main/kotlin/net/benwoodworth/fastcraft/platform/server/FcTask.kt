package net.benwoodworth.fastcraft.platform.server

import net.benwoodworth.fastcraft.util.Extensible

/**
 * A server task.
 */
interface FcTask : Extensible {

    val isRunning: Boolean

    /**
     * Start the task, if it is not already running.
     */
    fun start()

    /**
     * Stop the task if it's running.
     */
    fun stop()
}
