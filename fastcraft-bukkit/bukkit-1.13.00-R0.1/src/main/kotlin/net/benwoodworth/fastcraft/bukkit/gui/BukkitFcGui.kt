package net.benwoodworth.fastcraft.bukkit.gui

import net.benwoodworth.fastcraft.platform.event.FcEventListener
import net.benwoodworth.fastcraft.platform.gui.FcGui
import net.benwoodworth.fastcraft.platform.gui.FcGuiCloseEvent
import net.benwoodworth.fastcraft.platform.player.FcPlayer

class BukkitFcGui : FcGui {
    override val onClose: FcEventListener<FcGuiCloseEvent>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun open(vararg players: FcPlayer) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getViewers(): List<FcPlayer> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
