package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.bukkit.util.CauseTracker
import net.benwoodworth.fastcraft.events.HandlerSet
import net.benwoodworth.fastcraft.platform.player.FcOpenCraftingTableNaturallyEvent
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.player.FcPlayerInventoryChangeEvent
import net.benwoodworth.fastcraft.platform.player.FcPlayerJoinEvent
import net.benwoodworth.fastcraft.platform.server.FcTask
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.inventory.*
import org.bukkit.event.player.*
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.PluginManager
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
open class BukkitFcPlayerEvents_1_7 @Inject constructor(
    plugin: Plugin,
    private val fcPlayerProvider: FcPlayer.Provider,
    pluginManager: PluginManager,
    private val causeTracker: CauseTracker,
    private val fcTaskFactory: FcTask.Factory,
) : BukkitFcPlayerEvents {
    override val onPlayerJoin: HandlerSet<FcPlayerJoinEvent> = HandlerSet()
    override val onOpenCraftingTableNaturally: HandlerSet<FcOpenCraftingTableNaturallyEvent> = HandlerSet()
    override val onPlayerInventoryChange: HandlerSet<FcPlayerInventoryChangeEvent> = HandlerSet()

    private class PlayerInventoryChangeEvent<T : Event>(
        val eventType: Class<T>,
        private val getPlayer: (T) -> Player?,
    ) {
        fun getPlayer(event: Event): Player? = getPlayer.invoke(event as T)
    }

    private val inventoryChangeEvents = listOf(
        PlayerInventoryChangeEvent(InventoryInteractEvent::class.java) { it.inventory.holder as? Player },
        PlayerInventoryChangeEvent(InventoryDragEvent::class.java) { it.inventory.holder as? Player },
        PlayerInventoryChangeEvent(InventoryClickEvent::class.java) { it.inventory.holder as? Player },
        PlayerInventoryChangeEvent(InventoryPickupItemEvent::class.java) { it.inventory.holder as? Player },
        PlayerInventoryChangeEvent(PlayerItemConsumeEvent::class.java) { it.player },
        PlayerInventoryChangeEvent(PlayerItemBreakEvent::class.java) { it.player },
        PlayerInventoryChangeEvent(PlayerPickupItemEvent::class.java) { it.player },
        PlayerInventoryChangeEvent(PlayerDropItemEvent::class.java) { it.player },
    )

    init {
        val listener = EventListener()
        pluginManager.registerEvents(EventListener(), plugin)

        inventoryChangeEvents.forEach { invEvent ->
            pluginManager.registerEvent(
                invEvent.eventType,
                listener,
                EventPriority.MONITOR,
                { _, event -> invEvent.getPlayer(event)?.let { onPlayerInventoryChange(it) } },
                plugin,
                true
            )
        }
    }

    protected open fun Block.isCraftingTable(): Boolean {
        return type == Material.WORKBENCH
    }

    protected fun onPlayerInventoryChange(player: Player) {
        fcTaskFactory.startTask {
            onPlayerInventoryChange.notifyHandlers(
                BukkitFcPlayerInventoryChangeEvent(fcPlayerProvider.getPlayer(player))
            )
        }
    }

    private inner class EventListener : Listener {
        @EventHandler
        fun onPlayerJoin(event: PlayerJoinEvent) {
            onPlayerJoin.notifyHandlers(
                BukkitFcPlayerJoinEvent_1_7(event, fcPlayerProvider)
            )
        }

        @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
        fun onPlayerInteract(event: PlayerInteractEvent) {
            if (event.clickedBlock?.isCraftingTable() == true) {
                causeTracker.trackCause(CraftingTableInteractCause(event.player))
            }
        }

        @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
        fun onInventoryOpen(event: InventoryOpenEvent) {
            val player = event.player as? Player ?: return
            if (event.inventory.type == InventoryType.WORKBENCH &&
                causeTracker.checkCause(CraftingTableInteractCause(player))
            ) {
                onOpenCraftingTableNaturally.notifyHandlers(
                    BukkitFcOpenCraftingTableNaturallyEvent_1_7(event, fcPlayerProvider.getPlayer(player))
                )
            }
        }

        @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
        fun onInventoryInteract(event: InventoryInteractEvent) {
            (event.inventory.holder as? Player)?.let { onPlayerInventoryChange(it) }
        }

        @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
        fun onInventoryDrag(event: InventoryDragEvent) {
            (event.whoClicked as? Player)?.let { onPlayerInventoryChange(it) }
        }

        @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
        fun onInventoryClick(event: InventoryClickEvent) {
            (event.whoClicked as? Player)?.let { onPlayerInventoryChange(it) }
        }

        @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
        fun onInventoryPickupItem(event: InventoryPickupItemEvent) {
            (event.inventory.holder as? Player)?.let { onPlayerInventoryChange(it) }
        }

        @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
        fun onPlayerItemConsume(event: PlayerItemConsumeEvent) {
            (event.player)?.let { onPlayerInventoryChange(it) }
        }

        @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
        fun onPlayerItemBreak(event: PlayerItemBreakEvent) {
            (event.player)?.let { onPlayerInventoryChange(it) }
        }

        @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
        fun onPlayerPickupItem(event: PlayerPickupItemEvent) {
            (event.player)?.let { onPlayerInventoryChange(it) }
        }

        @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
        fun onPlayerDropItem(event: PlayerDropItemEvent) {
            (event.player)?.let { onPlayerInventoryChange(it) }
        }
    }

    private data class CraftingTableInteractCause(val player: Player)
}
