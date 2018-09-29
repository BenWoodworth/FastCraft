package net.benwoodworth.fastcraft.platform.bukkit.event

import net.benwoodworth.fastcraft.platform.api.event.FcEventPluginDisable
import net.benwoodworth.fastcraft.util.Adapter
import org.bukkit.event.server.PluginDisableEvent

/**
 * Bukkit implementation of [FcEventPluginDisable]
 */
class BukkitFcEventPluginDisable(
    override val base: PluginDisableEvent
) : FcEventPluginDisable, Adapter<PluginDisableEvent>()
