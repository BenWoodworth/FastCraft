package net.benwoodworth.fastcraft.impl.sponge.dependencies.server

import net.benwoodworth.fastcraft.core.dependencies.server.TaskScheduler
import org.spongepowered.api.scheduler.Task

/**
 * Sponge implementation of [TaskScheduler].
 */
class SpongeTaskScheduler : TaskScheduler {

    override fun sync(delayTicks: Long, task: () -> Unit) {
        Task.builder()
                .delayTicks(delayTicks)
                .execute(task)
    }

    override fun async(delayTicks: Long, task: () -> Unit) {
        Task.builder()
                .async()
                .delayTicks(delayTicks)
                .execute(task)
    }
}
