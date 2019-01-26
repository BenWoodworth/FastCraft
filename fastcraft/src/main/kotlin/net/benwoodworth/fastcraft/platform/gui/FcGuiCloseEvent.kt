package net.benwoodworth.fastcraft.platform.gui

import net.benwoodworth.fastcraft.platform.event.FcEvent
import net.benwoodworth.fastcraft.platform.player.FcPlayer

interface FcGuiCloseEvent : FcEvent {

    val gui: FcGui<*>

    val player: FcPlayer
}
