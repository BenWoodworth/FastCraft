package net.benwoodworth.fastcraft.implementations.sponge.api.gui

import net.benwoodworth.fastcraft.dependencies.api.gui.Gui
import net.benwoodworth.fastcraft.dependencies.api.gui.GuiAbstract
import net.benwoodworth.fastcraft.dependencies.api.gui.event.GuiEventClick
import net.benwoodworth.fastcraft.dependencies.api.gui.layout.GuiLayout
import net.benwoodworth.fastcraft.dependencies.api.gui.region.GuiRegion
import net.benwoodworth.fastcraft.dependencies.player.Player
import net.benwoodworth.fastcraft.implementations.sponge.SpongeFastCraft
import net.benwoodworth.fastcraft.implementations.sponge.api.item.SpongeItem
import net.benwoodworth.fastcraft.implementations.sponge.api.text.SpongeText
import net.benwoodworth.fastcraft.implementations.sponge.player.SpongePlayer
import org.spongepowered.api.Sponge
import org.spongepowered.api.item.inventory.Carrier
import org.spongepowered.api.item.inventory.Inventory
import org.spongepowered.api.item.inventory.ItemStack
import org.spongepowered.api.item.inventory.property.InventoryTitle
import org.spongepowered.api.item.inventory.type.CarriedInventory
import org.spongepowered.api.item.inventory.type.GridInventory
import org.spongepowered.api.text.Text as Sponge_Text

/**
 * Sponge implementation of [Gui].
 */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
abstract class SpongeGui<out TInv: Inventory>(
        plugin: SpongeFastCraft,
        invProvider: (SpongeGui<TInv>) -> TInv
) : GuiAbstract(), Carrier {

    private companion object {
        var registeredListeners = false
    }

    init {
        if (!registeredListeners) {
            Sponge.getEventManager().registerListeners(plugin, SpongeGuiListeners())
            registeredListeners = true
        }
    }

    @Suppress("LeakingThis")
    protected val inventory: TInv = invProvider(this).also {
        require(it is CarriedInventory<*>)
    }

    override fun getInventory() = inventory as CarriedInventory<*>

    override val title
        get() = inventory.archetype // TODO Correct?
                .getProperty(InventoryTitle::class.java)
                .get().value?.let { SpongeText(it) }

    override fun open(vararg players: Player) {
        for (player in players) {
            (player as SpongePlayer).base.openInventory(inventory)
        }
    }

    override fun getViewers(): List<Player> {
        return Sponge.getServer().onlinePlayers
                .filter { it.openInventory === inventory }
                .map(::SpongePlayer)
    }

    protected fun GuiLayout.getSpongeItem(x: Int, y: Int): ItemStack? {
        return (getItem(x, y)?.mutableCopy() as SpongeItem.Mutable?)?.base
    }

    abstract fun onClick(slotIndex: Int, guiEvent: GuiEventClick)

    abstract class GridBase(
            plugin: SpongeFastCraft,
            invProvider: (SpongeGui<GridInventory>) -> GridInventory
    ) : SpongeGui<GridInventory>(plugin, invProvider) {

        override val layout = addLayout(inventory.columns, inventory.rows)

        override fun updateLayout(region: GuiRegion) {
            for (x in 0 until layout.width) {
                for (y in 0 until layout.height) {
                    if (region.contains(x, y)) {
                        inventory.set(x, y, layout.getSpongeItem(x, y))
                    }
                }
            }
        }

        override fun onClick(slotIndex: Int, guiEvent: GuiEventClick) {
            layout.onClick(
                    slotIndex % layout.width,
                    slotIndex / layout.width,
                    guiEvent
            )
        }
    }

    class Chest(
            plugin: SpongeFastCraft,
            invProvider: (SpongeGui<GridInventory>) -> GridInventory
    ) : Gui.Chest, GridBase(plugin, invProvider)

    class Dispenser(
            plugin: SpongeFastCraft,
            invProvider: (SpongeGui<GridInventory>) -> GridInventory
    ) : Gui.Dispenser, GridBase(plugin, invProvider)

    class Hopper(
            plugin: SpongeFastCraft,
            invProvider: (SpongeGui<GridInventory>) -> GridInventory
    ) : Gui.Hopper, GridBase(plugin, invProvider)
}
