package net.benwoodworth.fastcraft.bukkit.bukkit_1_13_R01.server

import net.benwoodworth.fastcraft.platform.server.FcTask
import org.bukkit.Bukkit
import kotlin.math.ceil

class BukkitFcTask(
    builder: BukkitFcTaskBuilder
) : FcTask {

    private val plugin = builder.plugin
    private val async = builder.async

    private val delay = builder.delaySeconds?.let { seconds ->
        ceil(seconds * 20.0).toLong()
    }

    private val interval = builder.intervalSeconds?.let { seconds ->
        ceil(seconds * 20.0).toLong()
    }

    private var taskId: Int? = null

    private val runnable = { builder.action(this) }

    override val isScheduled: Boolean
        get() = taskId != null

    override fun schedule() {
        if (isScheduled) {
            return
        }

        taskId = Bukkit.getScheduler().run {
            when {
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

    override fun cancel() {
        taskId?.let {
            Bukkit.getScheduler().cancelTask(it)
        }
    }
}
