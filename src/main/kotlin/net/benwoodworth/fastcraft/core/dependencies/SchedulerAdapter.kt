package net.benwoodworth.fastcraft.core.dependencies

import net.benwoodworth.fastcraft.core.dependencies.util.Adapter

/**
 * Executes server tasks.
 */
abstract class SchedulerAdapter(baseScheduler: Any) : Adapter(baseScheduler) {

    /**
     * Schedule a task to be run on the main server thread.
     *
     * @param task the task to run
     * @param delay the number of server ticks before the task is run. (20 ticks per second
     */
    abstract fun scheduleSync(task: Runnable, delay: Long)

    /**
     * Schedule a task to be run asynchronously.
     *
     * @param task the task to run
     * @param delay the number of server ticks before the task is run. (20 ticks per second
     */
    abstract fun scheduleAsync(task: Runnable, delay: Long)

    /**
     * Run a task on the main server thread.
     *
     * @param task the task to run
     */
    abstract fun runSync(task: Runnable)

    /**
     * Run a task asynchronously.
     *
     * @param task the task to run
     */
    abstract fun runAsync(task: Runnable)
}
