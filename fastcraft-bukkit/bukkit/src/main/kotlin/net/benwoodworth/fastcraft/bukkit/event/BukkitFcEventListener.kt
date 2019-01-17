package net.benwoodworth.fastcraft.bukkit.event

import net.benwoodworth.fastcraft.platform.event.FcEvent
import net.benwoodworth.fastcraft.platform.event.FcEventListener

interface BukkitFcEventListener<TEvent : FcEvent> : FcEventListener<TEvent>
