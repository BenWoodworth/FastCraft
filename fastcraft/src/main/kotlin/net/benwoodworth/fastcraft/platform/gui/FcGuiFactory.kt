package net.benwoodworth.fastcraft.platform.gui

import net.benwoodworth.fastcraft.platform.gui.types.FcGuiChest
import net.benwoodworth.fastcraft.platform.gui.types.FcGuiDispenser
import net.benwoodworth.fastcraft.platform.gui.types.FcGuiHopper

interface FcGuiFactory {

    fun FcGuiChest(height: Int): FcGuiChest

    fun FcGuiDispenser(): FcGuiDispenser

    fun FcGuiHopper(): FcGuiHopper
}
