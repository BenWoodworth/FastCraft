package net.benwoodworth.fastcraft.bukkit.server

import net.benwoodworth.fastcraft.platform.server.FcTask
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin

class BukkitFcTask_1_13_00_R01(
    private val plugin: Plugin,
    private val async: Boolean,
    private val delay: Long?,
    private val interval: Long?,
    action: (task: FcTask) -> Unit
) : BukkitFcTask {

    private val action = { action(this) }

    private var taskId: Int? = null

    override val isScheduled: Boolean
        get() = taskId != null

    override fun schedule() {
        if (isScheduled) {
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

    override fun cancel() {
        taskId?.let {
            Bukkit.getScheduler().cancelTask(it)
            taskId = null
        }
    }
}
