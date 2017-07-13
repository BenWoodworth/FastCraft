package net.benwoodworth.fastcraft.impl.sponge.dependencies.server

import net.benwoodworth.fastcraft.core.dependencies.server.TaskScheduler
import net.benwoodworth.fastcraft.impl.sponge.SpongeFastCraft
import org.spongepowered.api.scheduler.Task

/**
 * Sponge implementation of [TaskScheduler].
 */
class SpongeTaskScheduler(
        private val fastCraft: SpongeFastCraft
) : TaskScheduler {

    override fun sync(delayTicks: Long, task: () -> Unit) {
        Task.builder()
                .delayTicks(delayTicks)
                .execute(task)
                .submit(fastCraft)
    }

    override fun async(delayTicks: Long, task: () -> Unit) {
        Task.builder()
                .async()
                .delayTicks(delayTicks)
                .execute(task)
                .submit(fastCraft)
    }
}
