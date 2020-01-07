package net.benwoodworth.fastcraft.platform.gui

import net.benwoodworth.fastcraft.events.HandlerSet
import net.benwoodworth.fastcraft.platform.player.FcPlayer

interface FcGui<TLayout : FcGuiLayout> {
    val layout: TLayout

    val player: FcPlayer

    val onClose: HandlerSet<FcGuiCloseEvent>

    fun open()

    fun close()
}
