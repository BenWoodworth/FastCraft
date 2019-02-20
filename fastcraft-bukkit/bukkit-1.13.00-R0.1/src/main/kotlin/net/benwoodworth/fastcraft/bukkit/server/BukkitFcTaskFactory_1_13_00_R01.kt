package net.benwoodworth.fastcraft.bukkit.server

import net.benwoodworth.fastcraft.platform.server.FcTask
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitScheduler
import javax.inject.Inject

class BukkitFcTaskFactory_1_13_00_R01 @Inject constructor(
    private val plugin: Plugin,
    private val scheduler: BukkitScheduler
) : BukkitFcTaskFactory {

    override fun FcTask(
        async: Boolean,
        delaySeconds: Double,
        intervalSeconds: Double,
        action: (task: FcTask) -> Unit
    ): FcTask {
        return BukkitFcTask_1_13_00_R01(
            plugin,
            async,
            (delaySeconds * 20.0).toLong(),
            (intervalSeconds * 20.0).toLong(),
            action,
            scheduler
        )
    }
}
