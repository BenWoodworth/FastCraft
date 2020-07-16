package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.bukkit.util.CauseTracker
import net.benwoodworth.fastcraft.events.HandlerSet
import net.benwoodworth.fastcraft.platform.player.FcOpenCraftingTableNaturallyEvent
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.player.FcPlayerJoinEvent
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.PluginManager
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class BukkitFcPlayerEvents_1_7 @Inject constructor(
    plugin: Plugin,
    private val fcPlayerProvider: FcPlayer.Provider,
    pluginManager: PluginManager,
    private val causeTracker: CauseTracker,
) : BukkitFcPlayerEvents {
    override val onPlayerJoin: HandlerSet<FcPlayerJoinEvent> = HandlerSet()

    override val onOpenCraftingTableNaturally: HandlerSet<FcOpenCraftingTableNaturallyEvent> = HandlerSet()

    init {
        pluginManager.registerEvents(EventListener(), plugin)
    }

    protected open fun Block.isCraftingTable(): Boolean {
        return type == Material.WORKBENCH
    }

    private inner class EventListener : Listener {
        private val awaitingInventoryEvents: Map<Player, Long> = WeakHashMap()

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
    }

    private data class CraftingTableInteractCause(val player: Player)
}
