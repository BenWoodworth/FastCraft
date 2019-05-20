package net.benwoodworth.fastcraft.platform.gui

import net.benwoodworth.fastcraft.platform.server.FcPlayer
import net.benwoodworth.fastcraft.platform.text.FcText

interface FcGuiFactory {

    fun openChestGui(
        player: FcPlayer,
        title: FcText? = null,
        height: Int = 3
    ): FcGui<FcGuiLayoutGrid>
}
