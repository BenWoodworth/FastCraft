package net.benwoodworth.fastcraft.dependencies.server

/**
 * Builds a task and executes it.
 */
interface TaskBuilder {

    /**
     * Build and execute the task.
     *
     * @return the built and executed task
     */
    fun execute(executable: (Task) -> Unit): Task

    /**
     * Whether the task should be executed off the main server thread.
     *
     * @return fluent interface
     */
    fun async(): TaskBuilder

    /**
     * The delay until the task is execute for the first time.
     *
     * @param ticks the number ticks
     * @return fluent interface
     */
    fun delay(ticks: Long): TaskBuilder

    /**
     * The time between each execution. Should be `0` if the task should not repeat.
     *
     * @return fluent interface
     */
    fun interval(ticks: Long): TaskBuilder
}