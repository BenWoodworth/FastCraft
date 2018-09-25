package net.benwoodworth.fastcraft.platform.bukkit.server

import net.benwoodworth.fastcraft.platform.server.FcTask
import net.benwoodworth.fastcraft.platform.server.FcTaskBuilder
import net.benwoodworth.fastcraft.platform.bukkit.BukkitFastCraft
import org.bukkit.scheduler.BukkitRunnable
import javax.inject.Inject

class BukkitFcTaskBuilder @Inject constructor(
        private val plugin: BukkitFastCraft
) : FcTaskBuilder {

    private var async = false
    private var delay = -1L
    private var interval = -1L

    override fun execute(executable: (FcTask) -> Unit): FcTask {
        val runnable = object : BukkitRunnable() {
            lateinit var bukkitTask: BukkitFcTask
            override fun run() = executable(bukkitTask)
        }

        val task = when {
            async -> runnable.runTaskTimerAsynchronously(plugin, delay, interval)
            else -> runnable.runTaskTimer(plugin, delay, interval)
        }

        runnable.bukkitTask = BukkitFcTask(task)
        return runnable.bukkitTask
    }

    override fun async() = also {
        async = true
    }

    override fun delay(ticks: Long) = also {
        delay = ticks
    }

    override fun interval(ticks: Long) = also {
        interval = ticks
    }
}