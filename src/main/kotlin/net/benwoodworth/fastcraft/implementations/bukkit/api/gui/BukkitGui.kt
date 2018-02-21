package net.benwoodworth.fastcraft.implementations.bukkit.api.gui

import net.benwoodworth.fastcraft.dependencies.api.gui.Gui
import net.benwoodworth.fastcraft.dependencies.api.gui.GuiAbstract
import net.benwoodworth.fastcraft.dependencies.api.gui.GuiLocation
import net.benwoodworth.fastcraft.dependencies.api.gui.GuiRegion
import net.benwoodworth.fastcraft.dependencies.api.gui.event.GuiEventClick
import net.benwoodworth.fastcraft.dependencies.api.player.FcPlayer
import net.benwoodworth.fastcraft.dependencies.api.text.FcText
import net.benwoodworth.fastcraft.implementations.bukkit.BukkitFastCraft
import net.benwoodworth.fastcraft.implementations.bukkit.api.item.BukkitFcItem
import net.benwoodworth.fastcraft.implementations.bukkit.api.player.BukkitFcPlayer
import net.benwoodworth.fastcraft.implementations.bukkit.api.text.BukkitFcText
import org.bukkit.Bukkit
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.entity.Player as Bukkit_Player

/**
 * Bukkit implementation of [Gui].
 */
abstract class BukkitGui(
        plugin: BukkitFastCraft,
        private val inventory: Inventory
) : GuiAbstract(), InventoryHolder {

    private companion object {
        var registeredListeners = false
    }

    init {
        if (!registeredListeners) {
            Bukkit.getPluginManager().registerEvents(BukkitGuiListeners(), plugin)
            registeredListeners = true
        }
    }

    override val title: FcText?
        get() = inventory.title?.let(::BukkitFcText)

    override fun getInventory() = inventory

    override fun open(vararg players: FcPlayer) {
        for (player in players) {
            (player as BukkitFcPlayer).base.openInventory(inventory)
        }
    }

    override fun getViewers(): List<FcPlayer> {
        return inventory.viewers
                .filterIsInstance<Bukkit_Player>()
                .map(::BukkitFcPlayer)
    }

    override fun updateLayout(region: GuiRegion) {
        for (slot in 0 until inventory.size) {
            getLayoutLocation(slot)?.run {
                if (region.contains(location)) {
                    val item = layout.getItem(location)?.mutableCopy() as BukkitFcItem.Mutable?
                    inventory.setItem(slot, item?.base)
                }
            }
        }
    }

    fun onClick(event: InventoryClickEvent) {
        getLayoutLocation(event.slot)?.run {
            layout.click(GuiEventClick(
                    location,
                    this@BukkitGui,
                    (event.whoClicked as? org.bukkit.entity.Player)?.let(::BukkitFcPlayer),
                    event.isLeftClick,
                    event.isRightClick,
                    event.click == ClickType.MIDDLE,
                    event.click == ClickType.DOUBLE_CLICK,
                    (event.hotbarButton + 1).takeUnless { it == 0 },
                    event.isShiftClick
            ))
        }
    }

    protected abstract fun getLayoutLocation(slot: Int): LayoutLocation?

    class Chest(plugin: BukkitFastCraft, inventory: Inventory) : BukkitGui(plugin, inventory), Gui.Chest {
        override val layout = addLayout(9, inventory.size / 9)
        override fun getLayoutLocation(slot: Int) = LayoutLocation(layout, GuiLocation(slot % 9, slot / 9))
    }

    class Dispenser(plugin: BukkitFastCraft, inventory: Inventory) : BukkitGui(plugin, inventory), Gui.Dispenser {
        override val layout = addLayout(3, 3)
        override fun getLayoutLocation(slot: Int) = LayoutLocation(layout, GuiLocation(slot % 3, slot / 3))
    }

    class Hopper(plugin: BukkitFastCraft, inventory: Inventory) : BukkitGui(plugin, inventory), Gui.Hopper {
        override val layout = addLayout(5, 1)
        override fun getLayoutLocation(slot: Int) = LayoutLocation(layout, GuiLocation(slot % 5, slot / 5))
    }
}
