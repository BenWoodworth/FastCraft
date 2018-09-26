package net.benwoodworth.fastcraft.platform.api.gui

import net.benwoodworth.fastcraft.platform.api.text.FcText

interface FcGuiBuilder {

    fun chest(height: Int): Typed

    fun dispenser(): Typed

    fun hopper(): Typed

    interface Typed {

        fun title(title: FcText)

        fun build(): FcGui
    }
}