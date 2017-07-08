package net.benwoodworth.fastcraft.impl.sponge.dependencies.server

import net.benwoodworth.fastcraft.core.dependencies.server.TaskScheduler
import org.spongepowered.api.scheduler.Task

/**
 * Sponge implementation of [TaskScheduler].
 */
class SpongeTaskScheduler : TaskScheduler {

    override fun scheduleSync(delayTicks: Long, task: () -> Unit) {
        Task.builder()
                .delayTicks(delayTicks)
                .execute(task)
    }

    override fun scheduleAsync(delayTicks: Long, task: () -> Unit) {
        Task.builder()
                .delayTicks(delayTicks)
                .async()
                .execute(task)
    }

    override fun runSync(task: () -> Unit) {
        Task.builder()
                .execute(task)
    }

    override fun runAsync(task: () -> Unit) {
        Task.builder()
                .async()
                .execute(task)
    }
}
