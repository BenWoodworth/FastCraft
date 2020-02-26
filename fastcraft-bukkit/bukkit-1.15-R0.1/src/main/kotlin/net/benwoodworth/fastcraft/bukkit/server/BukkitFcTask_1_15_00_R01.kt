package net.benwoodworth.fastcraft.bukkit.server

import net.benwoodworth.fastcraft.bukkit.BukkitFastCraftPlugin
import net.benwoodworth.fastcraft.platform.server.FcTask
import org.bukkit.scheduler.BukkitScheduler

class BukkitFcTask_1_15_00_R01(
    private val plugin: BukkitFastCraftPlugin,
    private val async: Boolean,
    private val delay: Long,
    private val interval: Long,
    action: (task: FcTask) -> Unit,
    private val scheduler: BukkitScheduler
) : BukkitFcTask {
    private val action = { action(this) }

    private var taskId: Int? = null

    override val isScheduled: Boolean
        get() = taskId != null

    override fun schedule() {
        if (isScheduled) {
            return
        }

        @Suppress("DEPRECATION")
        taskId = scheduler.run {
            when {
                async && interval != 0L ->
                    scheduleAsyncRepeatingTask(plugin, action, delay, interval)
                async ->
                    scheduleAsyncDelayedTask(plugin, action, delay)
                interval != 0L ->
                    scheduleSyncRepeatingTask(plugin, action, delay, interval)
                delay != 0L ->
                    scheduleSyncDelayedTask(plugin, action, delay)
                else ->
                    scheduleSyncDelayedTask(plugin, action)
            }
        }
    }

    override fun cancel() {
        taskId?.let {
            scheduler.cancelTask(it)
            taskId = null
        }
    }

    override fun equals(other: Any?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hashCode(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
