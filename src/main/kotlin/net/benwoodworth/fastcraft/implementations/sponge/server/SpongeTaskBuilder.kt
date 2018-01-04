package net.benwoodworth.fastcraft.implementations.sponge.server

import net.benwoodworth.fastcraft.dependencies.server.Task
import net.benwoodworth.fastcraft.dependencies.server.TaskBuilder
import net.benwoodworth.fastcraft.implementations.sponge.SpongeFastCraft
import net.benwoodworth.fastcraft.util.Adapter
import javax.inject.Inject

/**
 * Sponge implementation of [TaskBuilder].
 */
class SpongeTaskBuilder @Inject constructor(
        private val plugin: SpongeFastCraft
) : TaskBuilder, Adapter<org.spongepowered.api.scheduler.Task.Builder>(org.spongepowered.api.scheduler.Task.builder()) {

    override fun execute(executable: (Task) -> Unit): Task {
        base.execute({ task -> executable(SpongeTask(task)) })
        return SpongeTask(base.submit(plugin))
    }

    override fun async() = also {
        base.async()
    }

    override fun delay(ticks: Long) = also {
        base.delayTicks(ticks)
    }

    override fun interval(ticks: Long) = also {
        base.intervalTicks(ticks)
    }
}