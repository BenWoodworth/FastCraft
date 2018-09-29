package net.benwoodworth.fastcraft.platform.sponge.server

import net.benwoodworth.fastcraft.platform.api.server.FcTask
import net.benwoodworth.fastcraft.platform.api.server.FcTaskBuilder
import net.benwoodworth.fastcraft.platform.sponge.SpongeFastCraft
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.scheduler.Task
import javax.inject.Inject

/**
 * Sponge implementation of [FcTaskBuilder].
 */
class SpongeFcTaskBuilder @Inject constructor(
    private val plugin: SpongeFastCraft
) : FcTaskBuilder, Adapter<Task.Builder>() {

    override val base: Task.Builder = Task.builder()

    override fun execute(executable: (FcTask) -> Unit): FcTask {
        base.execute({ task -> executable(SpongeFcTask(task)) })
        return SpongeFcTask(base.submit(plugin))
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