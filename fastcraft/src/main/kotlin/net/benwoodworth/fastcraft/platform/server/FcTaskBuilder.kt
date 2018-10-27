package net.benwoodworth.fastcraft.platform.server

import net.benwoodworth.fastcraft.util.Extensible

/**
 * Builds a [FcTask] and executes it.
 */
interface FcTaskBuilder : Extensible {

    /**
     * Returns a new [FcTask].
     */
    fun build(): FcTask

    /**
     * Set the task's action.
     */
    fun action(action: (task: FcTask) -> Unit): FcTaskBuilder

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
