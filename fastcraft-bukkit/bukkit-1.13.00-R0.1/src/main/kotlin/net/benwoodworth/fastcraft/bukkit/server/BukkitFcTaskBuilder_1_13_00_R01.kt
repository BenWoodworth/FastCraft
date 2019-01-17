package net.benwoodworth.fastcraft.bukkit.server

import net.benwoodworth.fastcraft.platform.server.FcTask
import org.bukkit.plugin.Plugin

class BukkitFcTaskBuilder_1_13_00_R01(
    private val plugin: Plugin
) : BukkitFcTaskBuilder {

    override var action: (task: FcTask) -> Unit = {}

    override var async: Boolean = false

    override var delaySeconds: Double? = null

    override var intervalSeconds: Double? = null

    override fun build(): FcTask {
        return BukkitFcTask_1_13_00_R01(
            plugin,
            async,
            delaySeconds?.let { (it * 20).toLong() },
            intervalSeconds?.let { (it * 20).toLong() },
            action
        )
    }
}
