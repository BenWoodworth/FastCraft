package net.benwoodworth.fastcraft.bukkit.gui

import net.benwoodworth.fastcraft.platform.gui.FcGuiClickEvent
import net.benwoodworth.fastcraft.platform.gui.FcGuiLayout
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.text.FcLegacyText

class BukkitFcGui_1_13_00_R01<TLayout : FcGuiLayout> : BukkitFcGui<TLayout> {

    override val layout: TLayout
        get() = TODO("not implemented")
    override val title: FcLegacyText
        get() = TODO("not implemented")
    override val player: FcPlayer
        get() = TODO("not implemented")

    override fun close() {
        TODO("not implemented")
    }

    override fun setCloseHandler(handler: (event: FcGuiClickEvent) -> Unit) {
        TODO("not implemented")
    }

    override fun removeCloseHandler(handler: (event: FcGuiClickEvent) -> Unit) {
        TODO("not implemented")
    }
}
