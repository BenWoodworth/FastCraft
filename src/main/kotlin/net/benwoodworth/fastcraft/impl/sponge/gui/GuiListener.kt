package net.benwoodworth.fastcraft.impl.sponge.gui

import org.spongepowered.api.event.Listener
import org.spongepowered.api.event.item.inventory.AffectSlotEvent
import org.spongepowered.api.item.inventory.Slot
import org.spongepowered.api.item.inventory.type.CarriedInventory

/**
 * Listens for GUI inventory events.
 */
class GuiListener {

    /**
     * Determine if a [Slot] is in a [SpongeGui].
     *
     * @param slot the [Slot] to check
     * @return `true` iff the slot is in a [SpongeGui]
     */
    private fun isInGui(slot: Slot): Boolean {
        var ancestor = slot.parent()

        while (ancestor != null) {
            if (ancestor is CarriedInventory<*>
                    && ancestor.carrier.isPresent
                    && ancestor.carrier.get() is SpongeGui) {
                return true
            }

            ancestor = ancestor.parent()
        }

        return false
    }

    /**
     * Listen for inventory [Slot] changes, and cancel
     * any attempts to change a [SpongeGui].
     *
     * @param event the slot event
     */
    @Listener
    fun onAffectSlot(event: AffectSlotEvent) {
        event.transactions
                .filter { isInGui(it.slot) }
                .forEach { it.isValid = false }
    }
}
