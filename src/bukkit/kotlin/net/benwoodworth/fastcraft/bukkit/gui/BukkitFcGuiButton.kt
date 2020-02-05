package net.benwoodworth.fastcraft.bukkit.gui

import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import java.util.*

interface BukkitFcGuiButton : FcGuiButton {
    var locale: Locale
}

val FcGuiButton.locale: Locale
    get() = (this as BukkitFcGuiButton).locale
