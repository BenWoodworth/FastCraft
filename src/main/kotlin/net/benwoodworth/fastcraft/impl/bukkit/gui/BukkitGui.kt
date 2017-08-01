package net.benwoodworth.fastcraft.impl.bukkit.gui

import net.benwoodworth.fastcraft.dependencies.event.EventGuiButtonClick
import net.benwoodworth.fastcraft.dependencies.gui.Gui
import net.benwoodworth.fastcraft.dependencies.gui.GuiLayoutComposite
import net.benwoodworth.fastcraft.dependencies.player.Player
import net.benwoodworth.fastcraft.dependencies.text.Text
import net.benwoodworth.fastcraft.impl.bukkit.item.BukkitItem
import net.benwoodworth.fastcraft.impl.bukkit.player.BukkitPlayer
import net.benwoodworth.fastcraft.impl.bukkit.text.BukkitText
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.event.inventory.InventoryDragEvent
import org.bukkit.event.EventPriority
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.entity.Player as Bukkit_Player

/**
 * Bukkit implementation of [Gui].
 */
class BukkitGui(
        height: Int,
        title: String?
) : Gui, InventoryHolder, GuiLayoutComposite by GuiLayoutComposite.Impl(9, height) {

    init {
        changeListener += this::updateLayout
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

    override fun updateLayout() {
        for (x in 0 until width) {
            for (y in 0 until height) {
                val item = getButton(x, y)?.item?.mutableCopy() as BukkitItem.Mutable?
                inventory.setItem(x + y * width, item?.base)
            }
        }
    }

    /**
     * Listens for inventory events, in order to prevent modification
     * of the [Gui]'s inventory, and handling button clicks.
     */
    class Listeners : Listener {

        @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
        fun onInventoryClick(event: InventoryClickEvent) {
            val gui = event.inventory.holder as? Gui ?: return

            // If a GUI slot was clicked...
            if (event.rawSlot in 0 until event.inventory.size) {
                event.isCancelled = true

                // See if a button was clicked
                val button = gui.getButton(event.slot.rem(9), event.slot / 9)
                button?.clickListener?.notifyHandlers(EventGuiButtonClick.Impl(
                        gui,
                        button,
                        (event.whoClicked as? Bukkit_Player)?.let(::BukkitPlayer),
                        event.isLeftClick,
                        event.isRightClick,
                        event.click == ClickType.MIDDLE,
                        event.click == ClickType.DOUBLE_CLICK,
                        (event.hotbarButton + 1).takeUnless { it == 0 }
                ))
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
                    ClickType.CREATIVE -> {} // Click is allowed

                    else -> event.isCancelled = true
                }
            }
        }

        @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
        fun onInventoryDrag(event: InventoryDragEvent) {
            event.inventory.holder as? Gui ?: return

            // Cancel if dragged into GUI.
            for (i in event.rawSlots) {
                if (i == event.view.convertSlot(i)) {
                    event.isCancelled = true
                    return
                }
            }
        }
    }

    class Builder : Gui.Builder {

        private var height = 0
        private var title: String? = null

        override fun build() = BukkitGui(height, title)

        override fun height(height: Int) = also {
            this.height = height
        }

        override fun title(title: Text) = also {
            this.title = (title as BukkitText).text
        }
    }
}
