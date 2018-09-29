package net.benwoodworth.fastcraft.platform.api.server

/**
 * A server task.
 */
interface FcTask {

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
