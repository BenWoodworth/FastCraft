package net.benwoodworth.fastcraft.impl.bukkit.event

import net.benwoodworth.fastcraft.dependencies.event.EventPluginEnable
import net.benwoodworth.fastcraft.util.Adapter
import org.bukkit.event.server.PluginEnableEvent

/**
 * Bukkit implementation of [EventPluginEnable].
 */
class BukkitEventPluginEnable(
        baseEvent: PluginEnableEvent
) : EventPluginEnable, Adapter<PluginEnableEvent>(baseEvent)
