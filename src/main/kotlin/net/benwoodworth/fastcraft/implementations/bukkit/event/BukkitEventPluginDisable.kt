package net.benwoodworth.fastcraft.implementations.bukkit.event

import net.benwoodworth.fastcraft.dependencies.event.EventPluginDisable
import net.benwoodworth.fastcraft.util.Adapter
import org.bukkit.event.server.PluginDisableEvent

/**
 * Bukkit implementation of [EventPluginDisable]
 */
class BukkitEventPluginDisable(
        baseEvent: PluginDisableEvent
) : EventPluginDisable, Adapter<PluginDisableEvent>(baseEvent)
