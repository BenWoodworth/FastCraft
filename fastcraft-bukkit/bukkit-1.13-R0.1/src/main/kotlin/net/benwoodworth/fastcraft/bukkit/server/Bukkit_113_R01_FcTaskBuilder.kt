package net.benwoodworth.fastcraft.bukkit.server

import net.benwoodworth.fastcraft.platform.server.FcTask
import net.benwoodworth.fastcraft.platform.server.FcTaskBuilder
import org.bukkit.plugin.Plugin

@Suppress("ClassName")
class Bukkit_113_R01_FcTaskBuilder(
    private val plugin: Plugin
) : FcTaskBuilder {

    private var action: (task: FcTask) -> Unit = {}
    private var async: Boolean = false
    private var delay: Long? = null
    private var interval: Long? = null

    override fun action(action: (task: FcTask) -> Unit): FcTaskBuilder {
        this.action = action
        return this
    }

    override fun async(): FcTaskBuilder {
        async = true
        return this
    }

    override fun delay(ticks: Long): FcTaskBuilder {
        this.delay = ticks
        return this
    }

    override fun interval(ticks: Long): FcTaskBuilder {
        this.interval = ticks
        return this
    }

    override fun build(): FcTask {
        return Bukkit_113_R01_FcTask(plugin, action, async, delay, interval)
    }
}
