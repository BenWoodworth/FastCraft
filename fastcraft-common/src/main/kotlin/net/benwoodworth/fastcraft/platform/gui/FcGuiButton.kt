package net.benwoodworth.fastcraft.platform.gui

import net.benwoodworth.fastcraft.events.HandlerSet
import net.benwoodworth.fastcraft.platform.item.FcItem

interface FcGuiButton {

    val onClick: HandlerSet<FcGuiClickEvent>

    var item: FcItem?
}