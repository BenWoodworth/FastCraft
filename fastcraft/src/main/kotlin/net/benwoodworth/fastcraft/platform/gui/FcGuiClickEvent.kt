package net.benwoodworth.fastcraft.platform.gui

import net.benwoodworth.fastcraft.platform.event.FcEvent
import net.benwoodworth.fastcraft.platform.player.FcPlayer

interface FcGuiClickEvent : FcEvent {

    val gui: FcGui

    val player: FcPlayer?

    val primary: Boolean

    val secondary: Boolean

    val middle: Boolean

    val double: Boolean

    val number: Int?

    val shift: Boolean
}
