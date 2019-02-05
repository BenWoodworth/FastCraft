package net.benwoodworth.fastcraft.platform.gui

import net.benwoodworth.fastcraft.platform.item.FcItem

interface FcGuiButton {

    var clickHandler: ((event: FcGuiClickEvent) -> Unit)?

    fun setItem(item: FcItem?)
}