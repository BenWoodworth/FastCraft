package net.benwoodworth.fastcraft.bukkit.gui

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.bukkit.BukkitFastCraftPlugin
import net.benwoodworth.fastcraft.bukkit.player.player
import net.benwoodworth.fastcraft.events.HandlerSet
import net.benwoodworth.fastcraft.platform.gui.FcGuiCloseEvent
import net.benwoodworth.fastcraft.platform.gui.FcGuiLayout
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.event.inventory.*
import org.bukkit.event.server.PluginDisableEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.plugin.PluginManager

@AutoFactory
class BukkitFcGui_1_15_00_R01<TLayout : FcGuiLayout>(
    override val player: FcPlayer,
    createInventory: (owner: InventoryHolder) -> Inventory,
    createLayout: (inventory: Inventory) -> TLayout,
    @Provided plugin: BukkitFastCraftPlugin,
    @Provided pluginManager: PluginManager
) : BukkitFcGui<TLayout>, InventoryHolder, Listener {
    override val onClose: HandlerSet<FcGuiCloseEvent> = HandlerSet()

    private val inventory: Inventory = createInventory(this)

    override val layout: TLayout = createLayout(inventory)

    init {
        @Suppress("LeakingThis")
        pluginManager.registerEvents(this, plugin)

        player.player.openInventory(inventory)
    }

    override fun close() {
        player.player.openInventory?.let {
            if (it.topInventory.holder === this) {
                player.player.closeInventory()
            }
        }
    }

    override fun getInventory(): Inventory {
        return layout.inventory
    }

    private fun InventoryEvent.isGuiSlot(rawSlot: Int): Boolean {
        return rawSlot == view.convertSlot(rawSlot)
    }

    @Suppress("unused")
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun onInventoryClick(event: InventoryClickEvent) {
        if (event.inventory.holder !== this) {
            return
        }

        val slot = event.rawSlot
        if (event.isGuiSlot(slot)) {
            event.isCancelled = true

            layout.getSlotButton(slot)?.let {
                it.onClick(BukkitFcGuiClickEvent_1_15_00_R01(event, it))
            }
        } else {
            event.isCancelled = when (event.click) {
                // Clicks allowed outside the GUI inv
                ClickType.LEFT,
                ClickType.RIGHT,
                ClickType.WINDOW_BORDER_LEFT,
                ClickType.WINDOW_BORDER_RIGHT,
                ClickType.MIDDLE,
                ClickType.NUMBER_KEY,
                ClickType.DOUBLE_CLICK,
                ClickType.DROP,
                ClickType.CONTROL_DROP,
                ClickType.CREATIVE -> false

                // Clicks disallowed
                ClickType.SHIFT_LEFT,
                ClickType.SHIFT_RIGHT,
                ClickType.UNKNOWN -> true

                // Unhandled clicks
                else -> true
            }
        }
    }

    @Suppress("unused")
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    private fun onInventoryDrag(event: InventoryDragEvent) {
        if (event.inventory.holder !== this) {
            return
        }

        event.isCancelled = event.rawSlots
            .any { event.isGuiSlot(it) }
    }

    @Suppress("unused")
    @EventHandler(ignoreCancelled = true)
    private fun onInventoryClose(event: InventoryCloseEvent) {
        if (event.inventory.holder !== this) {
            return
        }

        HandlerList.unregisterAll(this)
        onClose.notifyHandlers(BukkitFcGuiCloseEvent_1_15_00_R01(this))
    }

    @Suppress("unused", "UNUSED_PARAMETER")
    @EventHandler(ignoreCancelled = true)
    private fun onPluginDisable(event: PluginDisableEvent) {
        close()
    }
}
