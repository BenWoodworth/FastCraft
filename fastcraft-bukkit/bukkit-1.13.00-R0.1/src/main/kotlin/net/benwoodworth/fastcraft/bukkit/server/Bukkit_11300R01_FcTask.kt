package net.benwoodworth.fastcraft.bukkit.server

import net.benwoodworth.fastcraft.platform.server.FcTask
import org.bukkit.Bukkit

@Suppress("ClassName")
class Bukkit_11300R01_FcTask(
    builder: Bukkit_11300R01_FcTaskBuilder
) : FcTask {

    private val plugin = builder.plugin
    private val action = { builder.action(this) }
    private val async = builder.async
    private val delay = builder.delay
    private val interval = builder.interval

    private var taskId: Int? = null

    override val isRunning: Boolean
        get() = taskId != null

    override fun start() {
        if (isRunning) {
            return
        }

        taskId = Bukkit.getScheduler().run {
            when {
                async && interval != null ->
                    scheduleAsyncRepeatingTask(plugin, action, delay ?: 0L, interval)
                async ->
                    scheduleAsyncDelayedTask(plugin, action, delay ?: 0L)
                interval != null ->
                    scheduleSyncRepeatingTask(plugin, action, delay ?: 0L, interval)
                delay != null ->
                    scheduleSyncDelayedTask(plugin, action, delay)
                else ->
                    scheduleSyncDelayedTask(plugin, action)
            }
        }
    }

    override fun stop() {
        taskId?.let {
            Bukkit.getScheduler().cancelTask(it)
        }
    }
}
