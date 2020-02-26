package net.benwoodworth.fastcraft.bukkit.gui

import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import org.bukkit.inventory.Inventory
import java.util.*

interface BukkitFcGuiButton : FcGuiButton {
    interface Factory {
        fun create(inventory: Inventory, slotIndex: Int, locale: Locale): FcGuiButton
    }

    var locale: Locale
}

val FcGuiButton.locale: Locale
    get() = (this as BukkitFcGuiButton).locale
