package net.benwoodworth.fastcraft.bukkit.bukkit_1_13_R01.server

import net.benwoodworth.fastcraft.platform.server.FcTask
import org.bukkit.plugin.Plugin

class BukkitFcTaskBuilder(
    val plugin: Plugin
) : BukkitFcTaskBuilder {

    override var action: (task: FcTask) -> Unit = {}

    override var async: Boolean = false

    override var delaySeconds: Double? = null

    override var intervalSeconds: Double? = null

    override fun build(): FcTask {
        return BukkitFcTask(this)
    }
}
