package net.benwoodworth.fastcraft.platform.gui

import net.benwoodworth.fastcraft.platform.player.FcPlayer

interface FcGuiFactory {

    fun openChestGui(
        player: FcPlayer,
        title: String? = null,
        height: Int = 3
    ): FcGui<FcGuiLayoutGrid>
}
