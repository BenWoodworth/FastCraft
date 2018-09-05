package net.benwoodworth.fastcraft.platform.server

/**
 * Builds a task and executes it.
 */
interface FcTaskBuilder {

    /**
     * Build and execute the task.
     *
     * @return the built and executed task.
     */
    fun execute(executable: (FcTask) -> Unit): FcTask

    /**
     * Whether the task should be executed off the main server thread.
     *
     * @return fluent interface.
     */
    fun async(): FcTaskBuilder

    /**
     * The delay until the task is execute for the first time.
     *
     * @param ticks the number ticks.
     * @return fluent interface.
     */
    fun delay(ticks: Long): FcTaskBuilder

    /**
     * The time between each execution. Should be `0` if the task should not repeat.
     *
     * @return fluent interface.
     */
    fun interval(ticks: Long): FcTaskBuilder
}