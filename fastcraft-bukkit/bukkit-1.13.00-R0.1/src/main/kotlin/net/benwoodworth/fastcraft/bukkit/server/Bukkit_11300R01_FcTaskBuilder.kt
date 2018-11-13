package net.benwoodworth.fastcraft.bukkit.server

import net.benwoodworth.fastcraft.platform.server.FcTask
import net.benwoodworth.fastcraft.platform.server.FcTaskBuilder
import org.bukkit.plugin.Plugin

@Suppress("ClassName")
class Bukkit_11300R01_FcTaskBuilder(
    val plugin: Plugin
) : FcTaskBuilder {

    override var action: (task: FcTask) -> Unit = {}

    override var async: Boolean = false

    override var delaySeconds: Double? = null

    override var intervalSeconds: Double? = null

    override fun build(): FcTask {
        return Bukkit_11300R01_FcTask(this)
    }
}
