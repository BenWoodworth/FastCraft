package net.benwoodworth.fastcraft.core.dependencies.server

/**
 * Executes server tasks.
 */
interface TaskScheduler {

    /**
     * Schedule a task to be run on the main server thread.
     *
     * @param task the task to run
     * @param delayTicks the number of server ticks before the task is run. (20 ticks per second)
     */
    fun sync(delayTicks: Long = 0, task: () -> Unit)

    /**
     * Schedule a task to be run asynchronously.
     *
     * @param task the task to run
     * @param delayTicks the number of server ticks before the task is run. (20 ticks per second)
     */
    fun async(delayTicks: Long = 0, task: () -> Unit)
}
