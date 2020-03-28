package net.benwoodworth.fastcraft.bukkit.server

import net.benwoodworth.fastcraft.platform.server.FcTask
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitScheduler
import javax.inject.Inject

class BukkitFcTaskFactory_1_7_5_R01 @Inject constructor(
    private val plugin: Plugin,
    private val scheduler: BukkitScheduler
) : BukkitFcTaskFactory {
    override fun startTask(
        async: Boolean,
        delaySeconds: Double,
        intervalSeconds: Double,
        action: (task: FcTask) -> Unit
    ): FcTask {
        return BukkitFcTask_1_7_5_R01(
            plugin = plugin,
            async = async,
            delay = (delaySeconds * 20.0).toLong(),
            interval = (intervalSeconds * 20.0).toLong(),
            action = action,
            scheduler = scheduler
        )
    }
}
