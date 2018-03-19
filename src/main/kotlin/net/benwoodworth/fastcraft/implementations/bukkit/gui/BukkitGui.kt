package net.benwoodworth.fastcraft.implementations.bukkit.gui

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.dependencies.gui.Gui
import net.benwoodworth.fastcraft.dependencies.gui.GuiAbstract
import net.benwoodworth.fastcraft.dependencies.gui.GuiLocation
import net.benwoodworth.fastcraft.dependencies.gui.GuiRegion
import net.benwoodworth.fastcraft.dependencies.gui.event.GuiEventClick
import net.benwoodworth.fastcraft.dependencies.player.FcPlayer
import net.benwoodworth.fastcraft.dependencies.text.FcText
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
sealed class BukkitGui : GuiAbstract(), InventoryHolder {

    protected abstract val baseInventory: Inventory
    protected abstract val playerFactory: BukkitFcPlayerFactory
    protected abstract val guiListener: BukkitGuiListener

    override fun getInventory() = baseInventory

    override fun open(vararg players: FcPlayer) {
        for (player in players) {
            (player as BukkitFcPlayer).base.openInventory(baseInventory)
        }
    }

    override fun getViewers(): List<FcPlayer> {
        return baseInventory.viewers
                .filterIsInstance<Player>()
                .map { playerFactory.create(it) }
    }

    override fun updateLayout(region: GuiRegion) {
        for (slot in 0 until baseInventory.size) {
            getLayoutLocation(slot)?.run {
                if (region.contains(location)) {
                    val item = layout.getItem(location)?.mutableCopy() as BukkitFcItem.Mutable?
                    baseInventory.setItem(slot, item?.base)
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

    @AutoFactory
    class Chest(
            height: Int,
            title: FcText?,

            @Provided private val server: Server,
            @Provided override val playerFactory: BukkitFcPlayerFactory,
            @Provided override val guiListener: BukkitGuiListener
    ) : BukkitGui(), Gui.Chest {
        override val layout = addLayout(9, inventory.size / 9)
        override fun getLayoutLocation(slot: Int) = LayoutLocation(layout, GuiLocation(slot % 9, slot / 9))

        override val baseInventory = Bukkit.
    }

    @AutoFactory
    class Dispenser(
            title: FcText?,

            @Provided private val server: Server,
            @Provided override val playerFactory: BukkitFcPlayerFactory,
            @Provided override val guiListener: BukkitGuiListener
    ) : BukkitGui(), Gui.Dispenser {
        override val layout = addLayout(3, 3)
        override fun getLayoutLocation(slot: Int) = LayoutLocation(layout, GuiLocation(slot % 3, slot / 3))
    }

    @AutoFactory
    class Hopper(
            title: FcText?,

            @Provided private val server: Server,
            @Provided override val playerFactory: BukkitFcPlayerFactory,
            @Provided override val guiListener: BukkitGuiListener
    ) : BukkitGui(), Gui.Hopper {
        override val layout = addLayout(5, 1)
        override fun getLayoutLocation(slot: Int) = LayoutLocation(layout, GuiLocation(slot % 5, slot / 5))
    }
}
