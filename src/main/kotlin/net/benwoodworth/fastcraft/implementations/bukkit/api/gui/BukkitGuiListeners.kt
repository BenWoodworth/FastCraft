package net.benwoodworth.fastcraft.implementations.bukkit.api.gui

import net.benwoodworth.fastcraft.dependencies.api.gui.Gui
import net.benwoodworth.fastcraft.dependencies.api.gui.event.GuiEventClose
import net.benwoodworth.fastcraft.implementations.bukkit.api.player.BukkitPlayer
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryDragEvent

/**
 * Listens for inventory events, in order to prevent modification
 * of the [Gui]'s inventory, and handling button clicks.
 */
@Suppress("UNUSED")
class BukkitGuiListeners : Listener { // TODO Move to own file

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    fun onInventoryClick(event: InventoryClickEvent) {
        val gui = event.inventory.holder as? BukkitGui ?: return
        if (event.isCancelled) return

        if (event.rawSlot in 0 until event.inventory.size) {
            // If a GUI slot was clicked...
            event.isCancelled = true
            gui.onClick(event)
        } else {
            when (event.click) {
                ClickType.LEFT,
                ClickType.RIGHT,
                ClickType.WINDOW_BORDER_LEFT,
                ClickType.WINDOW_BORDER_RIGHT,
                ClickType.MIDDLE,
                ClickType.NUMBER_KEY,
                ClickType.DROP,
                ClickType.CONTROL_DROP,
                ClickType.CREATIVE -> {
                    // Click is allowed
                }

                else -> event.isCancelled = true
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    fun onInventoryDrag(event: InventoryDragEvent) {
        event.inventory.holder as? BukkitGui ?: return

        // Cancel if dragged into GUI.
        for (i in event.rawSlots) {
            if (i == event.view.convertSlot(i)) {
                event.isCancelled = true
                return
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    fun onInventoryClose(event: InventoryCloseEvent) {
        val gui = event.inventory.holder as? BukkitGui ?: return
        val player = event.player as? Player

        gui.closeListener.notifyHandlers(
                GuiEventClose(gui, player?.let(::BukkitPlayer))
        )
    }
}