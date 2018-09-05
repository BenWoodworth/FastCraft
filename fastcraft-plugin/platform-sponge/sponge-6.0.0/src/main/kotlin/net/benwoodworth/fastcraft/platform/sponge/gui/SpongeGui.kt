package net.benwoodworth.fastcraft.platform.sponge.gui

import net.benwoodworth.fastcraft.platform.gui.Gui
import net.benwoodworth.fastcraft.platform.gui.GuiAbstract
import net.benwoodworth.fastcraft.platform.gui.GuiLocation
import net.benwoodworth.fastcraft.platform.gui.GuiRegion
import net.benwoodworth.fastcraft.platform.gui.event.GuiEventClick
import net.benwoodworth.fastcraft.platform.gui.layout.GuiLayout
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.sponge.SpongeFastCraft
import net.benwoodworth.fastcraft.platform.sponge.item.SpongeFcItem
import net.benwoodworth.fastcraft.platform.sponge.player.SpongeFcPlayer
import org.spongepowered.api.Sponge
import org.spongepowered.api.entity.living.player.Player
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent
import org.spongepowered.api.item.inventory.Carrier
import org.spongepowered.api.item.inventory.Inventory
import org.spongepowered.api.item.inventory.ItemStack
import org.spongepowered.api.item.inventory.property.SlotIndex
import org.spongepowered.api.item.inventory.type.CarriedInventory
import org.spongepowered.api.item.inventory.type.GridInventory

/**
 * Sponge implementation of [Gui].
 */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
abstract class SpongeGui<out TInv : Inventory>(
        invProvider: (SpongeGui<TInv>) -> TInv,

        @Suppress("UNUSED_PARAMETER")
        listener: SpongeGuiListener
) : GuiAbstract(), Carrier {

    @Suppress("LeakingThis")
    protected val inventory: TInv = invProvider(this).also {
        require(it is CarriedInventory<*>)
    }

    override fun getInventory() = inventory as CarriedInventory<*>

    override fun open(vararg players: FcPlayer) {
        for (player in players) {
            (player as SpongeFcPlayer).base.openInventory(inventory)
        }
    }

    override fun getViewers(): List<FcPlayer> {
        return Sponge.getServer().onlinePlayers
                .filter { it.openInventory === inventory }
                .map(::SpongeFcPlayer)
    }

    protected fun GuiLayout.getSpongeItem(x: Int, y: Int): ItemStack? {
        return (getItem(GuiLocation(x, y))?.mutableCopy() as SpongeFcItem.Mutable?)?.base
    }

    protected abstract fun getLayoutLocation(slotIndex: Int): LayoutLocation?

    fun onClick(event: ClickInventoryEvent, player: Player?) {
        val slotIndex = event
                .transactions[0]
                .slot
                .getProperty(SlotIndex::class.java, "slotindex")
                .map(SlotIndex::getValue).get()

        getLayoutLocation(slotIndex)?.run {
            layout.click(GuiEventClick(
                    location,
                    this@SpongeGui,
                    player?.let(::SpongeFcPlayer),
                    event is ClickInventoryEvent.Primary,
                    event is ClickInventoryEvent.Secondary,
                    event is ClickInventoryEvent.Middle,
                    event is ClickInventoryEvent.Double,
                    (event as? ClickInventoryEvent.NumberPress)?.number,
                    event is ClickInventoryEvent.Shift
            ))
        }
    }

    abstract class GridBase(
            plugin: SpongeFastCraft,
            invProvider: (SpongeGui<GridInventory>) -> GridInventory
    ) : SpongeGui<GridInventory>(plugin, invProvider) {

        override val layout = addLayout(inventory.columns, inventory.rows)

        override fun getLayoutLocation(slotIndex: Int): LayoutLocation? {
            if (slotIndex !in 0 until inventory.size()) {
                return null
            }

            return LayoutLocation(
                    layout,
                    GuiLocation(
                            slotIndex % layout.region.width,
                            slotIndex / layout.region.width
                    )
            )
        }

        override fun updateLayout(region: GuiRegion) {
            for (x in 0 until layout.region.width) {
                for (y in 0 until layout.region.height) {
                    if (region.contains(GuiLocation(x, y))) {
                        inventory.set(x, y, layout.getSpongeItem(x, y))
                    }
                }
            }
        }
    }

    class Chest(
            plugin: SpongeFastCraft,
            invProvider: (SpongeGui<GridInventory>) -> GridInventory
    ) : GridBase(plugin, invProvider), Gui.Chest

    class Dispenser(
            plugin: SpongeFastCraft,
            invProvider: (SpongeGui<GridInventory>) -> GridInventory
    ) : GridBase(plugin, invProvider), Gui.Dispenser

    class Hopper(
            plugin: SpongeFastCraft,
            invProvider: (SpongeGui<GridInventory>) -> GridInventory
    ) : GridBase(plugin, invProvider), Gui.Hopper
}
