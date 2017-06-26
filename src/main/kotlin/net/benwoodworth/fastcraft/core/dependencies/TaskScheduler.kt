package net.benwoodworth.fastcraft.core.dependencies

/**
 * Executes server tasks.
 */
interface TaskScheduler {

    /**
     * Schedule a task to be run on the main server thread.
     *
     * @param task the task to run
     * @param delayTicks the number of server ticks before the task is run. (20 ticks per second
     */
    fun scheduleSync(delayTicks: Long, task: () -> Unit)

    /**
     * Schedule a task to be run asynchronously.
     *
     * @param task the task to run
     * @param delayTicks the number of server ticks before the task is run. (20 ticks per second
     */
    fun scheduleAsync(delayTicks: Long, task: () -> Unit)

    /**
     * Run a task on the main server thread.
     *
     * @param task the task to run
     */
    fun runSync(task: () -> Unit)

    /**
     * Run a task asynchronously.
     *
     * @param task the task to run
     */
    fun runAsync(task: () -> Unit)
}
