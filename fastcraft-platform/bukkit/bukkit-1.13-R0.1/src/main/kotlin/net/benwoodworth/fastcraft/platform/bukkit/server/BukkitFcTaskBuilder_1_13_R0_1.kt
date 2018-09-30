package net.benwoodworth.fastcraft.platform.bukkit.server

import net.benwoodworth.fastcraft.platform.api.server.FcTask
import net.benwoodworth.fastcraft.platform.api.server.FcTaskBuilder
import org.bukkit.plugin.Plugin

@Suppress("ClassName")
class BukkitFcTaskBuilder_1_13_R0_1(
    private val plugin: Plugin
) : BukkitFcTaskBuilder {

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
        return BukkitFcTask_1_13_R0_1(plugin, action, async, delay, interval)
    }
}
