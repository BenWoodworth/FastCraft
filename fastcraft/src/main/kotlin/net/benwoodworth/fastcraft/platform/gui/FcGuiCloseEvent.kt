package net.benwoodworth.fastcraft.platform.gui

import net.benwoodworth.fastcraft.platform.event.FcEvent
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.util.Extensible

interface FcGuiCloseEvent : Extensible, FcEvent {

    val gui: FcGui

    val player: FcPlayer?
}
