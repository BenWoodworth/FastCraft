package net.benwoodworth.fastcraft.impl.bukkit.server

import net.benwoodworth.fastcraft.dependencies.server.Task
import net.benwoodworth.fastcraft.util.Adapter
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask as Bukkit_Task

/**
 * Bukkit implementation of [Task].
 */
class BukkitTask(
        task: Bukkit_Task
) : Task, Adapter<Bukkit_Task>(task) {

    override fun cancel() = base.cancel()

    class Builder(
            private val plugin: JavaPlugin
    ) : Task.Builder {

        private var async = false
        private var delay = 0L
        private var interval = 0L

        override fun run(executable: (Task) -> Unit): Task {
            val runnable = object : BukkitRunnable() {
                lateinit var bukkitTask: BukkitTask
                override fun run() = executable(bukkitTask)
            }

            val task = when {
                async && interval > 0 ->
                    runnable.runTaskTimerAsynchronously(plugin, delay, interval)

                async && delay > 0 ->
                    runnable.runTaskLaterAsynchronously(plugin, delay)

                async ->
                    runnable.runTaskAsynchronously(plugin)

                interval > 0 ->
                    runnable.runTaskTimer(plugin, delay, interval)

                delay > 0 ->
                    runnable.runTaskLater(plugin, delay)

                else ->
                    runnable.runTask(plugin)
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
