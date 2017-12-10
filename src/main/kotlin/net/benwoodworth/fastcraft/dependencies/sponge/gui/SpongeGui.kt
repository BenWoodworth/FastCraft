package net.benwoodworth.fastcraft.dependencies.sponge.gui

import net.benwoodworth.fastcraft.dependencies.abstractions.gui.Gui
import net.benwoodworth.fastcraft.dependencies.abstractions.event.EventGuiButtonClick
import net.benwoodworth.fastcraft.dependencies.abstractions.event.EventGuiClose
import net.benwoodworth.fastcraft.dependencies.abstractions.player.Player
import net.benwoodworth.fastcraft.dependencies.abstractions.text.Text
import net.benwoodworth.fastcraft.dependencies.sponge.item.SpongeItem
import net.benwoodworth.fastcraft.dependencies.sponge.player.SpongePlayer
import net.benwoodworth.fastcraft.dependencies.sponge.text.SpongeText
import org.spongepowered.api.Sponge
import org.spongepowered.api.entity.living.player.Player as Sponge_Player
import org.spongepowered.api.event.Listener as Sponge_Listener
import org.spongepowered.api.event.Order
import org.spongepowered.api.event.filter.cause.First
import org.spongepowered.api.event.item.inventory.AffectSlotEvent
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent
import org.spongepowered.api.event.item.inventory.InteractInventoryEvent
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
        height: Int,
        title: Sponge_Text?,
        plugin: Any
) : Gui(height), Carrier {

    init {
        changeListener += this::updateLayout
    }

    /**
     * The inventory representing this GUI.
     */
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
            .build(plugin) as CarriedInventory<SpongeGui>

    private val gridInventory = inventory.query<GridInventory>(GridInventory::class.java)

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

    override fun updateLayout() {
        for (x in 0 until width) {
            for (y in 0 until height) {
                val item = getButton(x, y)?.item?.mutableCopy() as SpongeItem.Mutable?
                gridInventory.set(x, y, item?.base)
            }
        }
    }

    /**
     * Listens for inventory events, in order to prevent modification
     * of the [Gui]'s inventory, and handling button clicks.
     */
    @Suppress("UNUSED")
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
        @Sponge_Listener(order = Order.EARLY)
        fun onAffectSlot(event: AffectSlotEvent) {
            event.transactions
                    .filter { isGuiSlot(it.slot) }
                    .forEach { it.isValid = false }
        }

        /**
         * Forwards inventory clicks to buttons.
         */
        @Sponge_Listener(order = Order.BEFORE_POST)
        fun onClick(event: ClickInventoryEvent, @First player: Sponge_Player?) {
            if (event.transactions.size != 1) {
                return
            }

            val slot = event.transactions[0].slot
            if (!isGuiSlot(slot)) {
                return
            }

            val carriedInv = slot.parent() as CarriedInventory<*>
            val gui = carriedInv.carrier.get() as SpongeGui

            val slotIndex = slot.getProperty(SlotIndex::class.java, "slotindex")
                    .map(SlotIndex::getValue).get()

            val button = gui.getButton(
                    slotIndex.rem(gui.width),
                    slotIndex / gui.width
            ) ?: return

            val clickEvent = EventGuiButtonClick.Impl(
                    gui,
                    button,
                    player?.let(::SpongePlayer),
                    event is ClickInventoryEvent.Primary,
                    event is ClickInventoryEvent.Secondary,
                    event is ClickInventoryEvent.Middle,
                    event is ClickInventoryEvent.Double,
                    (event as? ClickInventoryEvent.NumberPress)?.number
            )

            button.clickListener.notifyHandlers(clickEvent)
        }

        @Sponge_Listener(order = Order.LAST)
        fun onClose(event: InteractInventoryEvent.Close, @First player: Sponge_Player?) {
            if (event.isCancelled) {
                return
            }

            val carriedInv = event.targetInventory as? CarriedInventory<*> ?: return
            val gui = carriedInv.carrier as? Gui ?: return

            gui.closeListener.notifyHandlers(
                    EventGuiClose.Impl(gui, player?.let(::SpongePlayer))
            )
        }
    }

    /**
     * Sponge implementation of [Gui.Builder].
     */
    class Builder(
            private val plugin: Any
    ) : Gui.Builder {

        private var height: Int? = null
        private var title: Sponge_Text? = null

        override fun build(): Gui {
            return SpongeGui(
                    height!!,
                    title,
                    plugin
            )
        }

        override fun height(height: Int): Gui.Builder {
            this.height = height
            return this
        }

        override fun title(title: Text): Gui.Builder {
            this.title = (title as SpongeText).base
            return this
        }
    }
}
