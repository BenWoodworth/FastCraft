package net.benwoodworth.fastcraft.platform.gui

import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.text.FcLegacyText

interface FcGui<TLayout : FcGuiLayout> {

    val layout: TLayout

    val title: FcLegacyText?

    val player: FcPlayer

    val closeHandler: ((event: FcGuiCloseEvent) -> Unit)?

    fun close()
}
