package net.benwoodworth.fastcraft.bukkit.server

import net.benwoodworth.fastcraft.platform.server.FcTask
import org.bukkit.plugin.Plugin

class BukkitFcTaskFactory_1_13_00_R01(
    private val plugin: Plugin
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
            delaySeconds.let { (it * 20).toLong() },
            intervalSeconds?.let { (it * 20).toLong() },
            action
        )
    }
}
