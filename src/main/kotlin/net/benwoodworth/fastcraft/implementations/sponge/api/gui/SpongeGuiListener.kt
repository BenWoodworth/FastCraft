package net.benwoodworth.fastcraft.implementations.sponge.api.gui

import net.benwoodworth.fastcraft.dependencies.api.gui.Gui
import net.benwoodworth.fastcraft.dependencies.api.gui.event.GuiEventClose
import net.benwoodworth.fastcraft.implementations.sponge.SpongeFastCraft
import net.benwoodworth.fastcraft.implementations.sponge.api.player.SpongeFcPlayer
import org.spongepowered.api.Sponge
import org.spongepowered.api.entity.living.player.Player
import org.spongepowered.api.event.Listener
import org.spongepowered.api.event.Order
import org.spongepowered.api.event.filter.cause.First
import org.spongepowered.api.event.item.inventory.AffectSlotEvent
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent
import org.spongepowered.api.event.item.inventory.InteractInventoryEvent
import org.spongepowered.api.item.inventory.Slot
import org.spongepowered.api.item.inventory.property.SlotIndex
import org.spongepowered.api.item.inventory.type.CarriedInventory
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Listens for inventory events, in order to prevent modification
 * of the [Gui]'s inventory, and handling button clicks.
 */
@Singleton
class SpongeGuiListener @Inject constructor(
        plugin: SpongeFastCraft
) {

    init {
        Sponge.getEventManager().registerListeners(plugin, this)
    }

    /**
     * Determine if a slot is part of a [Gui].
     *
     * @return `true` iff the slot is in a [Gui].
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
     * @param event the [AffectSlotEvent].
     */
    @Suppress("UNUSED")
    @Listener(order = Order.EARLY)
    fun onAffectSlot(event: AffectSlotEvent) {
        event.transactions
                .filter { isGuiSlot(it.slot) }
                .forEach { it.isValid = false }
    }

    /**
     * Forwards inventory clicks to elements.
     */
    @Suppress("UNUSED")
    @Listener(order = Order.BEFORE_POST)
    fun onClick(event: ClickInventoryEvent, @First player: Player?) {
        if (event.transactions.size != 1) {
            return
        }

        val slot = event.transactions[0].slot
        if (!isGuiSlot(slot)) {
            return
        }

        val carriedInv = slot.parent() as CarriedInventory<*>
        val gui = carriedInv.carrier.get() as SpongeGui<*>

        gui.onClick(event, player)
    }

    @Suppress("UNUSED")
    @Listener(order = Order.LAST)
    fun onClose(event: InteractInventoryEvent.Close, @First player: Player?) {
        if (event.isCancelled) {
            return
        }

        val carriedInv = event.targetInventory as? CarriedInventory<*> ?: return
        val gui = carriedInv.carrier.orElse(null) as? Gui ?: return

        gui.closeListener.notifyHandlers(
                GuiEventClose(gui, player?.let(::SpongeFcPlayer))
        )
    }
}