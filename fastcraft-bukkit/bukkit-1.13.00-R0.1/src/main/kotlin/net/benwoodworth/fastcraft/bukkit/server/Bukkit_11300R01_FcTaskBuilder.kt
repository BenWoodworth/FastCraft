package net.benwoodworth.fastcraft.bukkit.server

import net.benwoodworth.fastcraft.platform.server.FcTask
import net.benwoodworth.fastcraft.platform.server.FcTaskBuilder
import org.bukkit.plugin.Plugin

@Suppress("ClassName")
class Bukkit_11300R01_FcTaskBuilder(
    internal val plugin: Plugin
) : FcTaskBuilder, FcTaskBuilder.WithAction {

    lateinit var action: (task: FcTask) -> Unit

    var async: Boolean = false
    var delay: Long? = null
    var interval: Long? = null

    override fun action(value: (task: FcTask) -> Unit): FcTaskBuilder {
        action = value
        return this
    }

    override fun async(value: Boolean): FcTaskBuilder {
        async = value
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
        return Bukkit_11300R01_FcTask(
            plugin,
            action,
            async,
            delay,
            interval
        )

    }
}
