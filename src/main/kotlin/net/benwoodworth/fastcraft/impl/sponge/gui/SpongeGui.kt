package net.benwoodworth.fastcraft.impl.sponge.gui

import net.benwoodworth.fastcraft.dependencies.gui.Gui
import net.benwoodworth.fastcraft.dependencies.gui.GuiLayoutComposite
import net.benwoodworth.fastcraft.dependencies.player.Player
import net.benwoodworth.fastcraft.impl.sponge.SpongeFastCraft
import net.benwoodworth.fastcraft.impl.sponge.item.SpongeItem
import net.benwoodworth.fastcraft.impl.sponge.player.SpongePlayer
import net.benwoodworth.fastcraft.impl.sponge.text.SpongeText
import org.spongepowered.api.Sponge
import org.spongepowered.api.event.cause.Cause
import org.spongepowered.api.event.item.inventory.InteractInventoryEvent
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
            .listener(InteractInventoryEvent::class.java, this::onInteractInventory)
            .build(fastCraft) as CarriedInventory<SpongeGui>

    private val gridInventory = inventory.query<GridInventory>(GridInventory::class.java)

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
        for (x in 0 until width) {
            for (y in 0 until height) {
                val spongeItem = getButton(x, y)?.item?.get() as SpongeItem?
                gridInventory.set(x, y, spongeItem?.base)
            }
        }
    }

    /**
     * Handle inventory interactions, preventing modifications
     * to this [Gui]'s inventory.
     */
    private fun onInteractInventory(event: InteractInventoryEvent) {
        if (event is InteractInventoryEvent.Open || event is InteractInventoryEvent.Close) {
            return
        }
        println(event)

        event.cursorTransaction

//        event.isCancelled = true
    }
}
