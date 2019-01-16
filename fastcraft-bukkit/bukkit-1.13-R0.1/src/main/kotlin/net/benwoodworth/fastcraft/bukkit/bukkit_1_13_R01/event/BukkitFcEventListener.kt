package net.benwoodworth.fastcraft.bukkit.bukkit_1_13_R01.event

import net.benwoodworth.fastcraft.platform.event.FcEvent
import net.benwoodworth.fastcraft.platform.event.FcEventHandler
import net.benwoodworth.fastcraft.platform.event.FcEventListener
import org.bukkit.Bukkit
import org.bukkit.event.Event
import org.bukkit.event.EventPriority
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.plugin.EventExecutor
import org.bukkit.plugin.Plugin

class Bukkit_11300R01_FcEventListener<TBukkitEvent : Event, TEvent : FcEvent>(
    private val plugin: Plugin,
    private val event: Class<TBukkitEvent>,
    private val priority: EventPriority = EventPriority.NORMAL,
    private val map: (TBukkitEvent) -> TEvent
) : FcEventListener<TEvent>, Listener, EventExecutor {

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

inline fun <reified TBukkitEvent : Event, reified TEvent : FcEvent> listener(
    plugin: Plugin,
    priority: EventPriority = EventPriority.NORMAL,
    noinline map: (TBukkitEvent) -> TEvent
): Bukkit_11300R01_FcEventListener<TBukkitEvent, TEvent> {
    return Bukkit_11300R01_FcEventListener(
        plugin = plugin,
        event = TBukkitEvent::class.java,
        priority = priority,
        map = map
    )
}
