package net.benwoodworth.fastcraft.bukkit.gui

import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import org.bukkit.inventory.Inventory
import java.util.*

interface FcGuiButton_Bukkit : FcGuiButton {
    var locale: Locale

    interface Factory {
        fun create(inventory: Inventory, slotIndex: Int, locale: Locale): FcGuiButton
    }
}

val FcGuiButton.locale: Locale
    get() = (this as FcGuiButton_Bukkit).locale
