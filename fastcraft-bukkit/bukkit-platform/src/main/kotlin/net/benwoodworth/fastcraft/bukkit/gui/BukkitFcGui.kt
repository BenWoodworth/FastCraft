package net.benwoodworth.fastcraft.bukkit.gui

import net.benwoodworth.fastcraft.platform.gui.FcGui
import net.benwoodworth.fastcraft.platform.gui.FcGuiLayout

interface FcGui_Bukkit<TLayout : FcGuiLayout> : FcGui<TLayout> {
    interface Factory : FcGui.Factory
}
