package net.benwoodworth.fastcraft.bukkit.event

import net.benwoodworth.fastcraft.platform.event.FcEvent
import net.benwoodworth.fastcraft.platform.event.FcEventListener
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

    companion object {
        inline fun <reified TBukkitEvent : Event, reified TEvent : FcEvent> create(
            plugin: Plugin,
            priority: EventPriority = EventPriority.NORMAL,
            noinline map: (TBukkitEvent) -> TEvent
        ): FcEventListener<TEvent> {
            return BukkitFcEventListener_1_13_00_R01(plugin, TBukkitEvent::class.java, priority, map)
        }
    }

    private val handlers = mutableSetOf<(event: TEvent) -> Unit>()

    override fun plusAssign(handler: (event: TEvent) -> Unit) {
        if (handlers.add(handler) && handlers.size == 1) {
            Bukkit.getPluginManager().registerEvent(event, this, priority, this, plugin, true)
        }
    }

    override fun minusAssign(handler: (event: TEvent) -> Unit) {
        if (handlers.remove(handler) && handlers.isEmpty()) {
            HandlerList.unregisterAll(this)
        }
    }

    override fun execute(listener: Listener, event: Event) {
        @Suppress("UNCHECKED_CAST")
        handlers.forEach {
            it(map(event as TBukkitEvent))
        }
    }
}
