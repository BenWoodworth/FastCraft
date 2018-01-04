package net.benwoodworth.fastcraft.implementations.bukkit.gui

import net.benwoodworth.fastcraft.dependencies.gui.EventGuiButtonClick
import net.benwoodworth.fastcraft.dependencies.gui.EventGuiClose
import net.benwoodworth.fastcraft.dependencies.gui.Gui
import net.benwoodworth.fastcraft.dependencies.player.Player
import net.benwoodworth.fastcraft.dependencies.text.Text
import net.benwoodworth.fastcraft.implementations.bukkit.BukkitFastCraft
import net.benwoodworth.fastcraft.implementations.bukkit.item.BukkitItem
import net.benwoodworth.fastcraft.implementations.bukkit.player.BukkitPlayer
import net.benwoodworth.fastcraft.implementations.bukkit.text.BukkitText
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryDragEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.entity.Player as Bukkit_Player
import org.bukkit.event.Listener as Bukkit_Listener

/**
 * Bukkit implementation of [Gui].
 */
class BukkitGui(
        plugin: BukkitFastCraft,
        height: Int,
        title: String?
) : Gui(height), InventoryHolder {

    private companion object {
        var registeredListeners = false
    }

    init {
        if (!registeredListeners) {
            Bukkit.getPluginManager().registerEvents(BukkitGuiListeners(), plugin)
            registeredListeners = true
        }
    }

    private val inventory: Inventory = Bukkit.createInventory(
            this,
            width * height,
            title
    )

    override fun getInventory() = inventory

    override val title: Text?
        get() = BukkitText(inventory.title)

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
        for (x in 0 until width) {
            for (y in 0 until height) {
                val item = getButton(x, y)?.item?.mutableCopy() as BukkitItem.Mutable?
                inventory.setItem(x + y * width, item?.base)
            }
        }
    }
}
