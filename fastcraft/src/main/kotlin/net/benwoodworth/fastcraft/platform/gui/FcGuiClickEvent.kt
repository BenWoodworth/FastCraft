package net.benwoodworth.fastcraft.platform.gui

import net.benwoodworth.fastcraft.platform.event.FcEvent

interface FcGuiClickEvent : FcEvent {

    val gui: FcGui<*>

    val button: FcGuiButton

    val isPrimaryClick: Boolean

    val isSecondaryClick: Boolean

    val isMiddleClick: Boolean

    val isDoubleClick: Boolean

    val isShiftClick: Boolean

    val isControlClick: Boolean

    val isDropClick: Boolean

    val numberKeyPressed: Int?
}
