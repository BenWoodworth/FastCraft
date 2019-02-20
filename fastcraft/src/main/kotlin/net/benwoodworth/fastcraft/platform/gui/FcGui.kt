package net.benwoodworth.fastcraft.platform.gui

import net.benwoodworth.fastcraft.events.HandlerSet
import net.benwoodworth.fastcraft.platform.server.FcPlayer
import net.benwoodworth.fastcraft.platform.text.FcLegacyText

interface FcGui<TLayout : FcGuiLayout> {

    val layout: TLayout

    val title: FcLegacyText?

    val player: FcPlayer

    val onClose: HandlerSet<FcGuiCloseEvent>

    fun close()
}
