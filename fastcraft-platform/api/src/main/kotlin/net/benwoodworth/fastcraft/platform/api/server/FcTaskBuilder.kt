package net.benwoodworth.fastcraft.platform.api.server

/**
 * Builds a task and executes it.
 */
interface FcTaskBuilder {

    /**
     * Execute the new task.
     *
     * @return the new task.
     */
    fun execute(executable: (task: FcTask) -> Unit): FcTask

    /**
     * Execute the new task.
     *
     * @return the new task.
     */
    fun execute(executable: () -> Unit): FcTask

    /**
     * Make the task execute asynchronously.
     */
    fun async(): FcTaskBuilder

    /**
     * Set the number of [ticks] before the task executes.
     */
    fun delay(ticks: Long): FcTaskBuilder

    /**
     * Set the number of [ticks] between each execution.
     */
    fun interval(ticks: Long): FcTaskBuilder
}