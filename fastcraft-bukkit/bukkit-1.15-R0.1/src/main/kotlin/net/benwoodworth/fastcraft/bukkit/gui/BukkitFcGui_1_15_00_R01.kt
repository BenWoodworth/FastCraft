package net.benwoodworth.fastcraft.bukkit.gui

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.bukkit.player.player
import net.benwoodworth.fastcraft.platform.gui.FcGui
import net.benwoodworth.fastcraft.platform.gui.FcGuiClick
import net.benwoodworth.fastcraft.platform.gui.FcGuiClickModifier
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
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.PluginManager

@AutoFactory
class BukkitFcGui_1_15_00_R01<TLayout : FcGuiLayout>(
    override val player: FcPlayer,
    createInventory: (owner: InventoryHolder) -> Inventory,
    createLayout: (inventory: Inventory) -> TLayout,
    @Provided plugin: Plugin,
    @Provided pluginManager: PluginManager
) : BukkitFcGui<TLayout>, InventoryHolder {
    override var listener: FcGui.Listener = FcGui.Listener.Default

    private val inventory: Inventory = createInventory(this)

    override val layout: TLayout = createLayout(inventory)

    init {
        @Suppress("LeakingThis")
        pluginManager.registerEvents(InventoryListener(), plugin)
    }

    override fun open() {
        player.player.openInventory(inventory)
    }

    override fun close() {
        if (player.player.openInventory.topInventory.holder === this) {
            player.player.closeInventory()
        }
    }

    override fun getInventory(): Inventory {
        return layout.inventory
    }

    private fun InventoryEvent.isGuiSlot(rawSlot: Int): Boolean {
        return rawSlot == view.convertSlot(rawSlot)
    }

    private fun InventoryClickEvent.getGui(): FcGui<*> {
        return view.topInventory.holder as FcGui<*>
    }

    private fun InventoryClickEvent.getGuiClick(): FcGuiClick {
        val modifiers = mutableSetOf<FcGuiClickModifier>()
            .apply {
                if (isShiftClick) {
                    add(FcGuiClickModifier.Shift)
                }

                if (click == ClickType.CONTROL_DROP) {
                    add(FcGuiClickModifier.Control)
                }

                if (click == ClickType.DOUBLE_CLICK) {
                    add(FcGuiClickModifier.DoubleClick)
                }
            }
            .toSet()

        return when {
            isLeftClick -> FcGuiClick.Primary(modifiers)
            isRightClick -> FcGuiClick.Secondary(modifiers)
            click == ClickType.MIDDLE -> FcGuiClick.Middle(modifiers)
            click == ClickType.DROP || click == ClickType.CONTROL_DROP -> FcGuiClick.Drop(modifiers)
            hotbarButton != -1 -> FcGuiClick.Number(hotbarButton, modifiers)
            else -> throw IllegalStateException("Unable to read click: $click")
        }
    }

    private inner class InventoryListener : Listener {
        @Suppress("unused")
        @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
        private fun onInventoryClick(event: InventoryClickEvent) {
            if (event.inventory.holder !== this@BukkitFcGui_1_15_00_R01) {
                return
            }

            val slot = event.rawSlot
            if (event.isGuiSlot(slot)) {
                event.isCancelled = true

                layout.getSlotButton(slot)?.let { button ->
                    try {
                        button.listener.onClick(event.getGui(), button, event.getGuiClick())
                    } catch (throwable: Throwable) {
                        throwable.printStackTrace()
                    }
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
            if (event.inventory.holder !== this@BukkitFcGui_1_15_00_R01) {
                return
            }

            event.isCancelled = event.rawSlots
                .any { event.isGuiSlot(it) }
        }

        @Suppress("unused")
        @EventHandler(ignoreCancelled = true)
        private fun onInventoryClose(event: InventoryCloseEvent) {
            if (event.inventory.holder !== this@BukkitFcGui_1_15_00_R01) {
                return
            }

            HandlerList.unregisterAll(this)
            listener.onClose()
        }

        @Suppress("unused", "UNUSED_PARAMETER")
        @EventHandler(ignoreCancelled = true)
        private fun onPluginDisable(event: PluginDisableEvent) {
            close()
        }
    }
}
