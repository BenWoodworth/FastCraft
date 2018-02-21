package net.benwoodworth.fastcraft.implementations.bukkit.server

import net.benwoodworth.fastcraft.dependencies.server.FcTask
import net.benwoodworth.fastcraft.util.Adapter
import org.bukkit.scheduler.BukkitTask as Bukkit_Task

/**
 * Bukkit implementation of [FcTask].
 */
class BukkitFcTask(
        task: Bukkit_Task
) : FcTask, Adapter<Bukkit_Task>(task) {

    override fun cancel() = base.cancel()
}
