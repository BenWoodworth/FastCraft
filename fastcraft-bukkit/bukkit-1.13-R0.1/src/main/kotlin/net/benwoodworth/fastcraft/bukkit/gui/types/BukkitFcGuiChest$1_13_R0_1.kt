package net.benwoodworth.fastcraft.bukkit.gui.types

import net.benwoodworth.fastcraft.platform.event.FcEventListener
import net.benwoodworth.fastcraft.platform.gui.FcGuiClickEvent
import net.benwoodworth.fastcraft.platform.gui.FcGuiCloseEvent
import net.benwoodworth.fastcraft.platform.gui.types.FcGuiChest
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.player.FcPlayer

@Suppress("ClassName")
class `BukkitFcGuiChest$1_13_R0_1` : FcGuiChest {
    override val width: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val height: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun setItem(row: Int, column: Int, item: FcItem, clickHandler: (event: FcGuiClickEvent) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeItem(row: Int, column: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val onClose: FcEventListener<FcGuiCloseEvent>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun open(vararg players: FcPlayer) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getViewers(): List<FcPlayer> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
