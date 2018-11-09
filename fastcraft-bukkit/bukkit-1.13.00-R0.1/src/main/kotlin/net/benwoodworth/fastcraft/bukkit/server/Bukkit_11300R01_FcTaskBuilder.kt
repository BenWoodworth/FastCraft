package net.benwoodworth.fastcraft.bukkit.server

import net.benwoodworth.fastcraft.platform.server.FcTask
import net.benwoodworth.fastcraft.platform.server.FcTaskBuilder
import org.bukkit.plugin.Plugin

@Suppress("ClassName")
class Bukkit_11300R01_FcTaskBuilder(
    internal val plugin: Plugin
) : FcTaskBuilder {

    internal var action: (task: FcTask) -> Unit = {}
    internal var async: Boolean = false
    internal var delay: Long? = null
    internal var interval: Long? = null

    override fun action(value: (task: FcTask) -> Unit): FcTaskBuilder {
        action = value
        return this
    }

    override fun async(): FcTaskBuilder {
        async = true
        return this
    }

    override fun tickDelay(value: Long): FcTaskBuilder {
        delay = value
        return this
    }

    override fun tickInterval(value: Long): FcTaskBuilder {
        interval = value
        return this
    }

    override fun build(): FcTask {
        return Bukkit_11300R01_FcTask(this)
    }
}
