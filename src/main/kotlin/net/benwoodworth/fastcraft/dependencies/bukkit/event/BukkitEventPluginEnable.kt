package net.benwoodworth.fastcraft.dependencies.bukkit.event

import net.benwoodworth.fastcraft.dependencies.abstractions.event.EventPluginEnable
import net.benwoodworth.fastcraft.util.Adapter
import org.bukkit.event.server.PluginEnableEvent

/**
 * Bukkit implementation of [EventPluginEnable].
 */
class BukkitEventPluginEnable(
        baseEvent: PluginEnableEvent
) : EventPluginEnable, Adapter<PluginEnableEvent>(baseEvent)
