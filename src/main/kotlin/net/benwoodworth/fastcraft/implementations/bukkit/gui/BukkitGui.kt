package net.benwoodworth.fastcraft.implementations.bukkit.gui

import net.benwoodworth.fastcraft.dependencies.gui.Gui
import net.benwoodworth.fastcraft.dependencies.gui.GuiAbstract
import net.benwoodworth.fastcraft.dependencies.gui.GuiLocation
import net.benwoodworth.fastcraft.dependencies.gui.GuiRegion
import net.benwoodworth.fastcraft.dependencies.gui.event.GuiEventClick
import net.benwoodworth.fastcraft.dependencies.player.FcPlayer
import net.benwoodworth.fastcraft.implementations.bukkit.BukkitFastCraft
import net.benwoodworth.fastcraft.implementations.bukkit.item.BukkitFcItem
import net.benwoodworth.fastcraft.implementations.bukkit.player.BukkitFcPlayer
import net.benwoodworth.fastcraft.implementations.bukkit.player.BukkitFcPlayerFactory
import org.bukkit.Server
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder

/**
 * Bukkit implementation of [Gui].
 */
sealed class BukkitGui(
        private val inventory: Inventory,
        private val playerFactory: BukkitFcPlayerFactory,

        @Suppress("UNUSED_PARAMETER")
        guiListener: BukkitGuiListener
) : GuiAbstract(), InventoryHolder {

    override fun getInventory() = inventory

    override fun open(vararg players: FcPlayer) {
        for (player in players) {
            (player as BukkitFcPlayer).base.openInventory(inventory)
        }
    }

    override fun getViewers(): List<FcPlayer> {
        return inventory.viewers
                .filterIsInstance<Player>()
                .map { playerFactory.create(it) }
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
                    (event.whoClicked as? Player)?.let { playerFactory.create(it) },
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

    class Chest(plugin: BukkitFastCraft, server: Server, inventory: Inventory) : BukkitGui(plugin, server, inventory), Gui.Chest {
        override val layout = addLayout(9, inventory.size / 9)
        override fun getLayoutLocation(slot: Int) = LayoutLocation(layout, GuiLocation(slot % 9, slot / 9))
    }

    class Dispenser(plugin: BukkitFastCraft, server: Server, inventory: Inventory) : BukkitGui(plugin, server, inventory), Gui.Dispenser {
        override val layout = addLayout(3, 3)
        override fun getLayoutLocation(slot: Int) = LayoutLocation(layout, GuiLocation(slot % 3, slot / 3))
    }

    class Hopper(plugin: BukkitFastCraft, server: Server, inventory: Inventory) : BukkitGui(plugin, server, inventory), Gui.Hopper {
        override val layout = addLayout(5, 1)
        override fun getLayoutLocation(slot: Int) = LayoutLocation(layout, GuiLocation(slot % 5, slot / 5))
    }
}
