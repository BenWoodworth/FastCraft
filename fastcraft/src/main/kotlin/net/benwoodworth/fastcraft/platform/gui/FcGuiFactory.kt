package net.benwoodworth.fastcraft.platform.gui

import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.text.FcLegacyText

interface FcGuiFactory {

    fun openChestGui(
        player: FcPlayer,
        title: FcLegacyText? = null,
        height: Int = 3
    ): FcGui<FcGuiLayoutGrid>
}
