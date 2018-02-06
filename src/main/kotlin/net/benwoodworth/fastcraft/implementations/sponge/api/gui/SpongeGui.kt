package net.benwoodworth.fastcraft.implementations.sponge.api.gui

import net.benwoodworth.fastcraft.api.gui.Gui
import net.benwoodworth.fastcraft.api.gui.GuiAbstract
import net.benwoodworth.fastcraft.dependencies.player.Player
import net.benwoodworth.fastcraft.implementations.sponge.item.SpongeItem
import net.benwoodworth.fastcraft.implementations.sponge.player.SpongePlayer
import net.benwoodworth.fastcraft.implementations.sponge.text.SpongeText
import org.spongepowered.api.Sponge
import org.spongepowered.api.item.inventory.Carrier
import org.spongepowered.api.item.inventory.property.InventoryTitle
import org.spongepowered.api.item.inventory.type.CarriedInventory
import org.spongepowered.api.text.Text as Sponge_Text

/**
 * Sponge implementation of [Gui].
 */
abstract class SpongeGui<TInv : CarriedInventory<SpongeGui<TInv>>>(
        plugin: Any,
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
    private val inventory = invProvider(this)

    override fun getInventory() = inventory

    override val title get() = inventory.archetype // TODO Correct?
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

    protected abstract fun getLayoutPos(slot: Int): LayoutPos?

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun updateLayout() {
        for (x in 0 until width) {
            for (y in 0 until height) {
                val item = getButton(x, y)?.item?.mutableCopy() as SpongeItem.Mutable?
                gridInventory.set(x, y, item?.base)
            }
        }
    }
}
