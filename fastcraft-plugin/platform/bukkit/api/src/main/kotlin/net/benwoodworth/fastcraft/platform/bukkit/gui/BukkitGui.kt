package net.benwoodworth.fastcraft.platform.bukkit.gui

import net.benwoodworth.fastcraft.platform.api.gui.Gui

interface BukkitGui : Gui {

    interface Chest : Gui.Chest

    interface Dispenser : Gui.Chest

    interface Hopper : Gui.Chest
}
