package net.benwoodworth.fastcraft.bukkit.gui

import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import org.bukkit.inventory.Inventory
import java.util.*

interface BukkitFcGuiButton : FcGuiButton {
    var locale: Locale

    companion object {
        val FcGuiButton.locale: Locale
            get() = (this as BukkitFcGuiButton).locale
    }

    interface Factory {
        fun create(inventory: Inventory, slotIndex: Int, locale: Locale): FcGuiButton
    }
}
