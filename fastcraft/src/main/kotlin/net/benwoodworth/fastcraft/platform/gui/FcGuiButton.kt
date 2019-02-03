package net.benwoodworth.fastcraft.platform.gui

import net.benwoodworth.fastcraft.platform.item.FcItem

interface FcGuiButton {

    var item: FcItem?

    var clickHandler: ((event: FcGuiClickEvent) -> Unit)?
}