package net.benwoodworth.fastcraft.bukkit.event

import net.benwoodworth.fastcraft.platform.event.FcEvent
import net.benwoodworth.fastcraft.platform.event.FcEventHandler
import org.bukkit.Bukkit
import org.bukkit.event.Event
import org.bukkit.event.EventPriority
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.plugin.EventExecutor
import org.bukkit.plugin.Plugin

class BukkitFcEventListener_1_13_00_R01<TBukkitEvent : Event, TEvent : FcEvent>(
    private val plugin: Plugin,
    private val event: Class<TBukkitEvent>,
    private val priority: EventPriority = EventPriority.NORMAL,
    private val map: (TBukkitEvent) -> TEvent
) : BukkitFcEventListener<TEvent>, Listener, EventExecutor {

    private val handlers = mutableSetOf<FcEventHandler<TEvent>>()

    override fun subscribe(handler: FcEventHandler<TEvent>) {
        if (handlers.add(handler) && handlers.size == 1) {
            Bukkit.getPluginManager().registerEvent(event, this, priority, this, plugin, true)
        }
    }

    override fun unsubscribe(handler: FcEventHandler<TEvent>) {
        if (handlers.remove(handler) && handlers.isEmpty()) {
            HandlerList.unregisterAll(this)
        }
    }

    override fun execute(listener: Listener, event: Event) {
        @Suppress("UNCHECKED_CAST")
        handlers.forEach {
            it(map(event as TBukkitEvent), this)
        }
    }
}
