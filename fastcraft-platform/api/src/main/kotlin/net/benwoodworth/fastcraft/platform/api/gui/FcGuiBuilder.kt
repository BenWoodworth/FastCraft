package net.benwoodworth.fastcraft.platform.api.gui

import net.benwoodworth.fastcraft.platform.api.gui.types.FcGuiChest
import net.benwoodworth.fastcraft.platform.api.gui.types.FcGuiDispenser
import net.benwoodworth.fastcraft.platform.api.gui.types.FcGuiHopper
import net.benwoodworth.fastcraft.platform.api.text.FcText

interface FcGuiBuilder {

    fun chest(height: Int): Typed<FcGuiChest>

    fun dispenser(): Typed<FcGuiDispenser>

    fun hopper(): Typed<FcGuiHopper>

    interface Typed<TFcGui : FcGui> {

        fun title(title: FcText)

        fun build(): TFcGui
    }
}