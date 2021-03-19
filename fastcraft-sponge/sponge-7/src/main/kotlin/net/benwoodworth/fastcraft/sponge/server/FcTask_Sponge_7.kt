package net.benwoodworth.fastcraft.sponge.server

import net.benwoodworth.fastcraft.platform.server.FcTask
import org.spongepowered.api.plugin.PluginContainer
import org.spongepowered.api.scheduler.Task
import javax.inject.Inject
import javax.inject.Singleton

class FcTask_Sponge_7(
    plugin: PluginContainer,
    async: Boolean,
    delay: Long,
    interval: Long,
    action: (task: FcTask) -> Unit,
) : FcTask_Sponge {
    override val task: Task = Task.builder().apply {
        if (async) async()
        if (delay > 0) delayTicks(delay)
        if (interval > 0) intervalTicks(interval)
        execute { -> action(this@FcTask_Sponge_7) }
    }.submit(plugin)

    override val isCancelled: Boolean //TODO Remove?
        get() = TODO()

    override fun cancel() {
        task.cancel()
    }

    override fun equals(other: Any?): Boolean {
        return other is FcTask_Sponge &&
                task == other.task
    }

    override fun hashCode(): Int {
        return task.hashCode()
    }

    @Singleton
    class Factory @Inject constructor(
        private val plugin: PluginContainer,
    ) : FcTask_Sponge.Factory {
        override fun startTask(
            async: Boolean,
            delayTicks: Long,
            intervalTicks: Long,
            action: (task: FcTask) -> Unit,
        ): FcTask {
            return FcTask_Sponge_7(
                plugin = plugin,
                async = async,
                delay = delayTicks,
                interval = intervalTicks,
                action = action,
            )
        }
    }
}
