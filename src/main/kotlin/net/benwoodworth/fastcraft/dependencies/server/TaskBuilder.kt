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
    fun run(executable: (Task) -> Unit): Task

    /**
     * Whether the task should be executed off the main server thread.
     *
     * @return fluid interface
     */
    fun async(): TaskBuilder

    /**
     * The delay until the task is run for the first time.
     *
     * @param ticks the number ticks
     * @return fluid interface
     */
    fun delay(ticks: Long): TaskBuilder

    /**
     * The time between each execution. Should be `0` if the task should not repeat.
     *
     * @return fluid interface
     */
    fun interval(ticks: Long): TaskBuilder
}
