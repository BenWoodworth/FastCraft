package net.benwoodworth.fastcraft.implementations.bukkit.event

import net.benwoodworth.fastcraft.dependencies.event.FcEventPluginEnable
import net.benwoodworth.fastcraft.util.Adapter
import org.bukkit.event.server.PluginEnableEvent

/**
 * Bukkit implementation of [FcEventPluginEnable].
 */
class BukkitFcEventPluginEnable(
        override val base: PluginEnableEvent
) : FcEventPluginEnable, Adapter<PluginEnableEvent>()
