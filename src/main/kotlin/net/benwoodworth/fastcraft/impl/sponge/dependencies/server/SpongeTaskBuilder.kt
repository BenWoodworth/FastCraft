package net.benwoodworth.fastcraft.impl.sponge.dependencies.server

import net.benwoodworth.fastcraft.core.dependencies.server.Task
import net.benwoodworth.fastcraft.core.dependencies.server.TaskBuilder
import net.benwoodworth.fastcraft.impl.sponge.SpongeFastCraft
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.scheduler.Task as Sponge_Task

/**
 * Sponge implementation of [TaskBuilder].
 */
class SpongeTaskBuilder(
        taskBuilder: Sponge_Task.Builder,
        private val fastCraft: SpongeFastCraft
) : TaskBuilder, Adapter<Sponge_Task.Builder>(taskBuilder) {

    override fun run(executable: (Task) -> Unit): Task {
        base.execute({ task -> executable(SpongeTask(task)) })
        return SpongeTask(base.submit(fastCraft))
    }

    override fun async(): TaskBuilder {
        base.async()
        return this
    }

    override fun delay(ticks: Long): TaskBuilder {
        base.delayTicks(ticks)
        return this
    }

    override fun interval(ticks: Long): TaskBuilder {
        base.intervalTicks(ticks)
        return this
    }
}
