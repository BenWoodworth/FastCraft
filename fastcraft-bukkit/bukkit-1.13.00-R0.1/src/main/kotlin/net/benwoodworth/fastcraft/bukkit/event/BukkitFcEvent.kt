package net.benwoodworth.fastcraft.bukkit.event

import net.benwoodworth.fastcraft.platform.event.FcEvent
import org.bukkit.event.Event

class BukkitFcEvent(
    val bukkitEvent: Event
) : FcEvent
