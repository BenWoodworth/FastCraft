package net.benwoodworth.fastcraft.implementations.sponge.server

import net.benwoodworth.fastcraft.dependencies.server.FcTask
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.scheduler.Task as Sponge_Task

/**
 * Sponge implementation of [FcTask].
 */
class SpongeFcTask(
        task: Sponge_Task
) : FcTask, Adapter<Sponge_Task>(task) {

    override fun cancel() {
        base.cancel()
    }
}
