package net.benwoodworth.fastcraft.platform.gui

import net.benwoodworth.fastcraft.platform.gui.types.FcGuiChest
import net.benwoodworth.fastcraft.platform.gui.types.FcGuiDispenser
import net.benwoodworth.fastcraft.platform.gui.types.FcGuiHopper
import net.benwoodworth.fastcraft.platform.text.FcText

interface FcGuiBuilder {

    fun chest(height: Int): Typed<FcGuiChest>

    fun dispenser(): Typed<FcGuiDispenser>

    fun hopper(): Typed<FcGuiHopper>

    interface Typed<TFcGui : FcGui> {

        fun title(title: FcText)

        fun build(): TFcGui
    }
}
