package net.benwoodworth.fastcraft.platform.gui

import net.benwoodworth.fastcraft.platform.player.FcPlayer

interface FcGui<TLayout : FcGuiLayout> {

    val layout: TLayout

    val title: String

    val player: FcPlayer

    fun close()

    fun setCloseHandler(handler: (event: FcGuiClickEvent) -> Unit)

    fun removeCloseHandler(handler: (event: FcGuiClickEvent) -> Unit)
}
