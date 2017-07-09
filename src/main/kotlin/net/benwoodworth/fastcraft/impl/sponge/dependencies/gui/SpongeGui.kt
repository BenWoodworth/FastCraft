package net.benwoodworth.fastcraft.impl.sponge.dependencies.gui

import net.benwoodworth.fastcraft.core.dependencies.gui.Gui
import net.benwoodworth.fastcraft.core.dependencies.gui.GuiLayoutComposite
import net.benwoodworth.fastcraft.core.dependencies.player.Player
import net.benwoodworth.fastcraft.impl.sponge.SpongeFastCraft
import net.benwoodworth.fastcraft.impl.sponge.dependencies.item.SpongeItem
import net.benwoodworth.fastcraft.impl.sponge.dependencies.player.SpongePlayer
import net.benwoodworth.fastcraft.impl.sponge.dependencies.text.SpongeText
import org.spongepowered.api.Sponge
import org.spongepowered.api.event.cause.Cause
import org.spongepowered.api.item.inventory.Carrier
import org.spongepowered.api.item.inventory.Inventory
import org.spongepowered.api.item.inventory.InventoryArchetypes
import org.spongepowered.api.item.inventory.property.InventoryDimension
import org.spongepowered.api.item.inventory.property.InventoryTitle
import org.spongepowered.api.item.inventory.type.CarriedInventory
import org.spongepowered.api.item.inventory.type.GridInventory
import org.spongepowered.api.text.Text as Sponge_Text

/**
 * Sponge implementation of [Gui].
 */
class SpongeGui(
        fastCraft: SpongeFastCraft,
        height: Int,
        title: Sponge_Text?
) : Gui, Carrier, GuiLayoutComposite by GuiLayoutComposite.Impl(0, 0) {

    init {
        changeListener += this::updateLayout
    }

    /** The inventory representing this GUI. */
    private val inventory: GridInventory = Inventory.builder()
                .of(InventoryArchetypes.CHEST)
                .property(InventoryDimension.PROPERTY_NAME, InventoryDimension(6, height))
                .property(InventoryTitle.PROPERTY_NAME, InventoryTitle(title))
                .withCarrier(this)
                .build(fastCraft) as GridInventory

    @Suppress("UNCHECKED_CAST")
    override fun getInventory() = inventory as CarriedInventory<SpongeGui>

    override val width get() = inventory.columns

    override val height get() = inventory.rows

    override val title get() = inventory.archetype
            .getProperty(InventoryTitle::class.java)
            .get().value?.let { SpongeText(it) }

    override fun open(vararg players: Player) {
        val cause = Cause.source(this).build()
        for (player in players) {
            (player as SpongePlayer).base.openInventory(inventory, cause)
        }
    }

    override fun getViewers(): List<Player> {
        return Sponge.getServer().onlinePlayers
                .filter { it.openInventory == inventory }
                .map { SpongePlayer(it) }
                .toList()
    }

    override fun updateLayout() {
        for (x in 0 until width) {
            for (y in 0 until height) {
                val item = getButton(x, y)?.item?.get()
                inventory.set(x, y, (item as SpongeItem).base)
            }
        }
    }
}
