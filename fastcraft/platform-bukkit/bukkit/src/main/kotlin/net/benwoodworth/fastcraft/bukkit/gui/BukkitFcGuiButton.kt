package net.benwoodworth.fastcraft.bukkit.gui

import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.text.FcLocale
import org.bukkit.inventory.ItemStack

interface BukkitFcGuiButton : FcGuiButton {

    var locale: FcLocale
}