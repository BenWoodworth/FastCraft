package net.benwoodworth.fastcraft.bukkit.gui

import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.text.FcLocale

interface BukkitFcGuiButton : FcGuiButton {
    var locale: FcLocale
}

val FcGuiButton.locale: FcLocale
    get() = (this as BukkitFcGuiButton).locale
