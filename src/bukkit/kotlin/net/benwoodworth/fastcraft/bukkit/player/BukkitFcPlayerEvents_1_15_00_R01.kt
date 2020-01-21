package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.bukkit.BukkitFastCraftPlugin
import net.benwoodworth.fastcraft.events.HandlerSet
import net.benwoodworth.fastcraft.platform.player.FcPlayerJoinEvent
import net.benwoodworth.fastcraft.platform.player.FcPlayerOpenWorkbenchEvent
import net.benwoodworth.fastcraft.platform.player.FcPlayerProvider
import org.bukkit.Material
import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.PluginManager
import javax.inject.Inject

class BukkitFcPlayerEvents_1_15_00_R01 @Inject constructor(
    plugin: BukkitFastCraftPlugin,
    private val playerProvider: FcPlayerProvider,
    pluginManager: PluginManager
) : BukkitFcPlayerEvents {
    override val onPlayerJoin: HandlerSet<FcPlayerJoinEvent> = HandlerSet()

    override val onPlayerOpenWorkbench: HandlerSet<FcPlayerOpenWorkbenchEvent> = HandlerSet()

    init {
        pluginManager.registerEvents(EventListener(), plugin)
    }

    private inner class EventListener : Listener {
        @EventHandler
        fun onPlayerJoin(event: PlayerJoinEvent) {
            onPlayerJoin.notifyHandlers(
                BukkitFcPlayerJoinEvent_1_15_00_R01(event, playerProvider)
            )
        }

        @EventHandler(priority = EventPriority.HIGHEST)
        fun onPlayerInteract(event: PlayerInteractEvent) {
            if (event.useInteractedBlock() == Event.Result.DENY) return
            if (event.action != Action.RIGHT_CLICK_BLOCK) return
            if (event.player.isSneaking) return
            if (event.clickedBlock?.type != Material.CRAFTING_TABLE) return

            onPlayerOpenWorkbench.notifyHandlers(
                BukkitFcPlayerOpenWorkbenchEvent_1_15_00_R01(event, playerProvider)
            )
        }
    }
}
