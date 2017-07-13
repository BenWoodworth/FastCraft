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
) : Gui, Carrier, GuiLayoutComposite by GuiLayoutComposite.Impl(9, height) {

    init {
        changeListener += this::updateLayout
    }

    /** The inventory representing this GUI. */
    @Suppress("UNCHECKED_CAST")
    private val inventory = Inventory.builder()
            .of(InventoryArchetypes.CHEST)
            .property(
                    InventoryDimension.PROPERTY_NAME,
                    InventoryDimension(width, height)
            )
            .property(
                    InventoryTitle.PROPERTY_NAME,
                    InventoryTitle(title)
            )
            .withCarrier(this)
            .build(fastCraft) as CarriedInventory<SpongeGui>

    override fun getInventory() = inventory

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
        val gridInv = inventory.query<GridInventory>(GridInventory::class.java)
        for (x in 0 until width) {
            for (y in 0 until height) {
                val spongeItem = getButton(x, y)?.item?.get() as SpongeItem?
                gridInv.set(x, y, spongeItem?.base)
            }
        }
    }
}
