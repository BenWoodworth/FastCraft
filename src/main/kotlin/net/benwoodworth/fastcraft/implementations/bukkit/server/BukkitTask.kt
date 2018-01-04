package net.benwoodworth.fastcraft.implementations.bukkit.server

import net.benwoodworth.fastcraft.dependencies.server.Task
import net.benwoodworth.fastcraft.util.Adapter
import org.bukkit.scheduler.BukkitTask as Bukkit_Task

/**
 * Bukkit implementation of [Task].
 */
class BukkitTask(
        task: Bukkit_Task
) : Task, Adapter<Bukkit_Task>(task) {

    override fun cancel() = base.cancel()
}
