package net.benwoodworth.fastcraft.impl.sponge.server

import net.benwoodworth.fastcraft.dependencies.server.Task
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.scheduler.Task as Sponge_Task

/**
 * Sponge implementation of [Task].
 */
class SpongeTask(
        task: Sponge_Task
) : Task, Adapter<Sponge_Task>(task) {

    override fun cancel() {
        base.cancel()
    }

    /**
     * Sponge implementation of [Task.Builder].
     */
    class Builder(
            taskBuilder: Sponge_Task.Builder,
            private val plugin: Any
    ) : Task.Builder, Adapter<Sponge_Task.Builder>(taskBuilder) {

        override fun run(executable: (Task) -> Unit): Task {
            base.execute({ task -> executable(SpongeTask(task)) })
            return SpongeTask(base.submit(plugin))
        }

        override fun async(): Task.Builder {
            base.async()
            return this
        }

        override fun delay(ticks: Long): Task.Builder {
            base.delayTicks(ticks)
            return this
        }

        override fun interval(ticks: Long): Task.Builder {
            base.intervalTicks(ticks)
            return this
        }
    }
}
