package net.benwoodworth.fastcraft.platform.bukkit.event

import net.benwoodworth.fastcraft.platform.api.event.FcEventPluginEnable
import net.benwoodworth.fastcraft.util.Adapter
import org.bukkit.event.server.PluginEnableEvent

/**
 * Bukkit implementation of [FcEventPluginEnable].
 */
class BukkitFcEventPluginEnable(
        override val base: PluginEnableEvent
) : FcEventPluginEnable, Adapter<PluginEnableEvent>()
