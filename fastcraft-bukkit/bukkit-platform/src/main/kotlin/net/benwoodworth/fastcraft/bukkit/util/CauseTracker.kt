package net.benwoodworth.fastcraft.bukkit.util

import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitScheduler
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Tracks causes/effects within the same tick.
 */
@Singleton
class CauseTracker @Inject constructor(
    private val scheduler: BukkitScheduler,
    private val plugin: Plugin,
) {
    private var clearCausesTask: Int = -1

    private val causes: MutableSet<Any> = mutableSetOf()

    fun trackCause(cause: Any) {
        causes += cause

        if (clearCausesTask == -1) {
            clearCausesTask = scheduler.scheduleSyncDelayedTask(plugin, {
                clearCausesTask = -1
                causes.clear()
            }, 0L)
        }
    }

    fun checkCause(cause: Any): Boolean {
        return cause in causes
    }
}
