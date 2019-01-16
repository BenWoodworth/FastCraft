package net.benwoodworth.fastcraft.platform.gui

import net.benwoodworth.fastcraft.platform.event.FcEventListener
import net.benwoodworth.fastcraft.platform.player.FcPlayer

interface FcGui {

    val onClose: FcEventListener<FcGuiCloseEvent>

    fun open(vararg players: FcPlayer)

    fun getViewers(): List<FcPlayer>
}
