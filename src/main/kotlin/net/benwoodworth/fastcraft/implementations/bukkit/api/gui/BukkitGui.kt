package net.benwoodworth.fastcraft.implementations.bukkit.api.gui

import net.benwoodworth.fastcraft.api.gui.Gui
import net.benwoodworth.fastcraft.api.gui.GuiAbstract
import net.benwoodworth.fastcraft.api.gui.event.GuiEventClick
import net.benwoodworth.fastcraft.dependencies.player.Player
import net.benwoodworth.fastcraft.dependencies.text.Text
import net.benwoodworth.fastcraft.implementations.bukkit.BukkitFastCraft
import net.benwoodworth.fastcraft.implementations.bukkit.item.BukkitItem
import net.benwoodworth.fastcraft.implementations.bukkit.player.BukkitPlayer
import net.benwoodworth.fastcraft.implementations.bukkit.text.BukkitText
import org.bukkit.Bukkit
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

    override val title: Text?
        get() = inventory.title?.let(::BukkitText)

    override fun getInventory() = inventory

    override fun open(vararg players: Player) {
        for (player in players) {
            (player as BukkitPlayer).base.openInventory(inventory)
        }
    }

    override fun getViewers(): List<Player> {
        return inventory.viewers
                .filterIsInstance<Bukkit_Player>()
                .map(::BukkitPlayer)
    }

    override fun updateLayout() {
        for (slot in 0 until inventory.size) {
            val layoutPos = getLayoutPos(slot)
            val item = layoutPos?.run {
                layout.getItem(x, y)?.mutableCopy() as BukkitItem.Mutable?
            }
            inventory.setItem(slot, item?.base)
        }
    }

    fun onClick(slot: Int, event: GuiEventClick) {
        getLayoutPos(slot)?.run {
            layout.onClick(x, y, event)
        }
    }

    protected abstract fun getLayoutPos(slot: Int): LayoutPos?

    class Chest(plugin: BukkitFastCraft, inventory: Inventory) : Gui.Chest, BukkitGui(plugin, inventory) {
        override val layout = addLayout(9, inventory.size / 9)
        override fun getLayoutPos(slot: Int) = LayoutPos(layout, slot % 9, slot / 9)
    }

    class Dispenser(plugin: BukkitFastCraft, inventory: Inventory) : Gui.Dispenser, BukkitGui(plugin, inventory) {
        override val layout = addLayout(3, 3)
        override fun getLayoutPos(slot: Int) = LayoutPos(layout, slot % 3, slot / 3)
    }

    class Hopper(plugin: BukkitFastCraft, inventory: Inventory) : Gui.Hopper, BukkitGui(plugin, inventory) {
        override val layout = addLayout(5, 1)
        override fun getLayoutPos(slot: Int) = LayoutPos(layout, slot % 5, slot / 5)
    }
}
