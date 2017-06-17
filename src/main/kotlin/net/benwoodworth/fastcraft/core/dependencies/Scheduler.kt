package net.benwoodworth.fastcraft.core.dependencies

/**
 * Executes server tasks.
 */
interface Scheduler {

    /**
     * Schedule a task to be run on the main server thread.
     *
     * @param task the task to run
     * @param delay the number of server ticks before the task is run. (20 ticks per second
     */
    fun scheduleSync(task: Runnable, delay: Long)

    /**
     * Schedule a task to be run asynchronously.
     *
     * @param task the task to run
     * @param delay the number of server ticks before the task is run. (20 ticks per second
     */
    fun scheduleAsync(task: Runnable, delay: Long)

    /**
     * Run a task on the main server thread.
     *
     * @param task the task to run
     */
    fun runSync(task: Runnable)

    /**
     * Run a task asynchronously.
     *
     * @param task the task to run
     */
    fun runAsync(task: Runnable)
}
