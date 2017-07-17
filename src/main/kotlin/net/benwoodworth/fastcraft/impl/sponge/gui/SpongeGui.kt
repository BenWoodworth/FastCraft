package net.benwoodworth.fastcraft.impl.sponge.gui

import net.benwoodworth.fastcraft.dependencies.gui.Gui
import net.benwoodworth.fastcraft.dependencies.gui.GuiLayoutComposite
import net.benwoodworth.fastcraft.dependencies.gui.events.EventGuiButtonClick
import net.benwoodworth.fastcraft.dependencies.player.Player
import net.benwoodworth.fastcraft.dependencies.text.Text
import net.benwoodworth.fastcraft.impl.sponge.SpongeFastCraft
import net.benwoodworth.fastcraft.impl.sponge.item.SpongeItem
import net.benwoodworth.fastcraft.impl.sponge.player.SpongePlayer
import net.benwoodworth.fastcraft.impl.sponge.text.SpongeText
import org.spongepowered.api.Sponge
import org.spongepowered.api.event.Listener
import org.spongepowered.api.event.Order
import org.spongepowered.api.event.cause.Cause
import org.spongepowered.api.event.item.inventory.AffectSlotEvent
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent
import org.spongepowered.api.item.inventory.Carrier
import org.spongepowered.api.item.inventory.Inventory
import org.spongepowered.api.item.inventory.InventoryArchetypes
import org.spongepowered.api.item.inventory.Slot
import org.spongepowered.api.item.inventory.property.InventoryDimension
import org.spongepowered.api.item.inventory.property.InventoryTitle
import org.spongepowered.api.item.inventory.property.SlotIndex
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
     * Listens for inventory events, in order to prevent modification
     * of the [Gui]'s inventory, and handling button clicks.
     */
    class Listeners {

        /**
         * Determine if a slot is part of a [Gui].
         *
         * @return `true` iff the slot is in a [Gui]
         */
        private fun isGuiSlot(slot: Slot): Boolean {
            val parent = slot.parent() as? CarriedInventory<*> ?: return false
            val carrier = parent.carrier.orElse(null) ?: return false

            val slotIndex = slot.getProperty(SlotIndex::class.java, "slotindex")
                    .map(SlotIndex::getValue)
                    .orElse(null)

            return slotIndex in 0 until carrier.inventory.capacity()
        }

        /**
         * Disables slot transactions in [Gui] inventories.
         *
         * @param event the [AffectSlotEvent]
         */
        @Listener(order = Order.EARLY)
        @Suppress("UNUSED")
        fun onAffectSlot(event: AffectSlotEvent) {
            event.transactions
                    .filter { isGuiSlot(it.slot) }
                    .forEach { it.isValid = false }
        }

        @Listener(order = Order.BEFORE_POST)
        fun onClick(event: ClickInventoryEvent) {
            if (event.transactions.size != 1) {
                return
            }

            val slot = event.transactions[0].slot
            if (!isGuiSlot(slot)) {
                return
            }

            val clickEventBuilder: EventGuiButtonClick

            when (event) {
                is ClickInventoryEvent.Drag -> {}
                //is
            }

        }
    }

    /**
     * Sponge implementation of [Gui.Builder].
     */
    class Builder (
            private val fastCraft: SpongeFastCraft
    ) : Gui.Builder {

        private var height: Int? = null
        private var title: Sponge_Text? = null

        override fun build(): Gui {
            return SpongeGui(
                    fastCraft,
                    height!!,
                    title
            )
        }

        override fun setHeight(height: Int): Gui.Builder {
            this.height = height
            return this
        }

        override fun setTitle(title: Text): Gui.Builder {
            this.title = (title as SpongeText).base
            return this
        }
    }
}
