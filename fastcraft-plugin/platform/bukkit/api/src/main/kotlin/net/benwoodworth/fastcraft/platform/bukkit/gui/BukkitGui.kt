package net.benwoodworth.fastcraft.platform.bukkit.gui

import net.benwoodworth.fastcraft.platform.api.gui.FcGui

interface BukkitGui : FcGui {

    interface Chest : FcGui.Chest

    interface Dispenser : FcGui.Chest

    interface Hopper : FcGui.Chest
}
