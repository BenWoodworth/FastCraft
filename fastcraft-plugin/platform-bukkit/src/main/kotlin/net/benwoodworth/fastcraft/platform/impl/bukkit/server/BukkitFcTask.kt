package net.benwoodworth.fastcraft.platform.impl.bukkit.server

import net.benwoodworth.fastcraft.platform.server.FcTask
import net.benwoodworth.fastcraft.util.Adapter
import org.bukkit.scheduler.BukkitTask

/**
 * Bukkit implementation of [FcTask].
 */
class BukkitFcTask(
        override val base: BukkitTask
) : FcTask, Adapter<BukkitTask>() {

    override fun cancel() = base.cancel()
}
