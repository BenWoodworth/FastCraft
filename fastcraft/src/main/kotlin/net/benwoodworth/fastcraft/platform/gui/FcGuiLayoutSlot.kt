package net.benwoodworth.fastcraft.platform.gui

import net.benwoodworth.fastcraft.platform.item.FcItem

interface FcGuiLayoutSlot {

    var item: FcItem?

    var clickHandler: ((event: FcGuiClickEvent) -> Unit)?
}