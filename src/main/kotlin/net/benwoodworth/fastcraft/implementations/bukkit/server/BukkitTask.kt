package net.benwoodworth.fastcraft.implementations.bukkit.server

import net.benwoodworth.fastcraft.dependencies.server.Task
import net.benwoodworth.fastcraft.implementations.bukkit.BukkitFastCraft
import net.benwoodworth.fastcraft.util.Adapter
import org.bukkit.scheduler.BukkitRunnable
import javax.inject.Inject
import org.bukkit.scheduler.BukkitTask as Bukkit_Task

/**
 * Bukkit implementation of [Task].
 */
class BukkitTask(
        task: Bukkit_Task
) : Task, Adapter<Bukkit_Task>(task) {

    override fun cancel() = base.cancel()

    class Builder @Inject constructor(
            private val plugin: BukkitFastCraft
    ) : Task.Builder {

        private var async = false
        private var delay = -1L
        private var interval = -1L

        override fun execute(executable: (Task) -> Unit): Task {
            val runnable = object : BukkitRunnable() {
                lateinit var bukkitTask: BukkitTask
                override fun run() = executable(bukkitTask)
            }

            val task = when {
                async -> runnable.runTaskTimerAsynchronously(plugin, delay, interval)
                else -> runnable.runTaskTimer(plugin, delay, interval)
            }

            runnable.bukkitTask = BukkitTask(task)
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
}
