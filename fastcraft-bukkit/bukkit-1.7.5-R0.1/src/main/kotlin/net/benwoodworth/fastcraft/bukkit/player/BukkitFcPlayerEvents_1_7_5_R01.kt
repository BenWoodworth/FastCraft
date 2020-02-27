package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.events.HandlerSet
import net.benwoodworth.fastcraft.platform.player.FcPlayerJoinEvent
import net.benwoodworth.fastcraft.platform.player.FcPlayerOpenWorkbenchEvent
import net.benwoodworth.fastcraft.platform.player.FcPlayerProvider
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.PluginManager
import javax.inject.Inject

open class BukkitFcPlayerEvents_1_7_5_R01 @Inject constructor(
    plugin: Plugin,
    private val playerProvider: FcPlayerProvider,
    pluginManager: PluginManager
) : BukkitFcPlayerEvents {
    override val onPlayerJoin: HandlerSet<FcPlayerJoinEvent> = HandlerSet()

    override val onPlayerOpenWorkbench: HandlerSet<FcPlayerOpenWorkbenchEvent> = HandlerSet()

    init {
        pluginManager.registerEvents(EventListener(), plugin)
    }

    open protected fun Block.isCraftingTable(): Boolean {
        return type == Material.WORKBENCH
    }

    private inner class EventListener : Listener {
        @EventHandler
        fun onPlayerJoin(event: PlayerJoinEvent) {
            onPlayerJoin.notifyHandlers(
                BukkitFcPlayerJoinEvent_1_7_5_R01(event, playerProvider)
            )
        }

        @EventHandler(priority = EventPriority.HIGHEST)
        fun onPlayerInteract(event: PlayerInteractEvent) {
            if (event.useInteractedBlock() == Event.Result.DENY) return
            if (event.action != Action.RIGHT_CLICK_BLOCK) return
            if (event.player.isSneaking) return
            if (event.clickedBlock?.isCraftingTable() != true) return

            onPlayerOpenWorkbench.notifyHandlers(
                BukkitFcPlayerOpenWorkbenchEvent_1_7_5_R01(event, playerProvider)
            )
        }
    }
}
