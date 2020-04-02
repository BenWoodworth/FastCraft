package net.benwoodworth.fastcraft.bukkit.server

import net.benwoodworth.fastcraft.platform.server.FcTask
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitScheduler

class BukkitFcTask_1_7_5_R01(
    plugin: Plugin,
    async: Boolean,
    delay: Long,
    interval: Long,
    action: (task: FcTask) -> Unit,
    private val scheduler: BukkitScheduler,
) : BukkitFcTask {
    override val taskId: Int

    init {
        val runnable = Runnable { action(this) }

        @Suppress("DEPRECATION")
        taskId = scheduler.run {
            when {
                async && interval != 0L ->
                    scheduleAsyncRepeatingTask(plugin, runnable, delay, interval)
                async ->
                    scheduleAsyncDelayedTask(plugin, runnable, delay)
                interval != 0L ->
                    scheduleSyncRepeatingTask(plugin, runnable, delay, interval)
                delay != 0L ->
                    scheduleSyncDelayedTask(plugin, runnable, delay)
                else ->
                    scheduleSyncDelayedTask(plugin, runnable)
            }
        }
    }

    override val isCancelled: Boolean
        get() = !scheduler.isCurrentlyRunning(taskId)

    override fun cancel() {
        scheduler.cancelTask(taskId)
    }

    override fun equals(other: Any?): Boolean {
        return other is BukkitFcTask &&
                taskId == other.taskId
    }

    override fun hashCode(): Int {
        return taskId
    }
}
