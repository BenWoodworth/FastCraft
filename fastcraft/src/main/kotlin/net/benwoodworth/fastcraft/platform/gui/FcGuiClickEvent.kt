package net.benwoodworth.fastcraft.platform.gui

import net.benwoodworth.fastcraft.platform.event.FcEvent
import net.benwoodworth.fastcraft.platform.player.FcPlayer

interface FcGuiClickEvent : FcEvent {

    val gui: FcGui<*>

    val player: FcPlayer

    val isPrimaryClick: Boolean

    val isSecondaryClick: Boolean

    val isMiddleClick: Boolean

    val isDoubleClick: Boolean

    val isShiftClick: Boolean

    val numberKeyPressed: Int?
}
