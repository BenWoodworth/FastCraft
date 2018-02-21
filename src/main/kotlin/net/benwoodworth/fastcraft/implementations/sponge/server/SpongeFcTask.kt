package net.benwoodworth.fastcraft.implementations.sponge.server

import net.benwoodworth.fastcraft.dependencies.server.FcTask
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.scheduler.Task

/**
 * Sponge implementation of [FcTask].
 */
class SpongeFcTask(
        task: Task
) : FcTask, Adapter<Task>(task) {

    override fun cancel() {
        base.cancel()
    }
}
