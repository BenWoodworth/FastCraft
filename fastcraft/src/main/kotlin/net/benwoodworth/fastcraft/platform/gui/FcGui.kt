package net.benwoodworth.fastcraft.platform.gui

import net.benwoodworth.fastcraft.platform.player.FcPlayer

interface FcGui<TLayout : FcGuiLayout> {
    val layout: TLayout
    val player: FcPlayer
    var listener: Listener

    fun open()

    fun close()

    interface Listener {
        object Default : Listener

        fun onClose() {}
    }
}
