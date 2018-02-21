package net.benwoodworth.fastcraft.implementations.sponge.server

import net.benwoodworth.fastcraft.dependencies.server.FcTask
import net.benwoodworth.fastcraft.dependencies.server.FcTaskBuilder
import net.benwoodworth.fastcraft.implementations.sponge.SpongeFastCraft
import net.benwoodworth.fastcraft.util.Adapter
import javax.inject.Inject

/**
 * Sponge implementation of [FcTaskBuilder].
 */
class SpongeFcTaskBuilder @Inject constructor(
        private val plugin: SpongeFastCraft
) : FcTaskBuilder, Adapter<org.spongepowered.api.scheduler.Task.Builder>(org.spongepowered.api.scheduler.Task.builder()) {

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