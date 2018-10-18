package net.benwoodworth.fastcraft.platform.bukkit.server

import net.benwoodworth.fastcraft.platform.server.FcTask
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin

@Suppress("ClassName")
class `BukkitFcTask$1_13_R0_1`(
    private val plugin: Plugin,
    private val action: (task: FcTask) -> Unit,
    private val async: Boolean,
    private val delay: Long?,
    private val interval: Long?
) : BukkitFcTask {

    private var taskId: Int? = null

    private val runnable = { action(this) }

    override val isRunning: Boolean
        get() = taskId != null

    override fun start() {
        if (isRunning) {
            return
        }

        with(Bukkit.getScheduler()) {
            taskId = when {
                async && interval != null ->
                    scheduleAsyncRepeatingTask(plugin, runnable, delay ?: 0L, interval)
                async ->
                    scheduleAsyncDelayedTask(plugin, runnable, delay ?: 0L)
                interval != null ->
                    scheduleSyncRepeatingTask(plugin, runnable, delay ?: 0L, interval)
                delay != null ->
                    scheduleSyncDelayedTask(plugin, runnable, delay)
                else ->
                    scheduleSyncDelayedTask(plugin, runnable)
            }
        }
    }

    override fun stop() {
        taskId?.let {
            Bukkit.getScheduler().cancelTask(it)
        }
    }
}
