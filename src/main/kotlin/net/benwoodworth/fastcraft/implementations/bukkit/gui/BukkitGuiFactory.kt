package net.benwoodworth.fastcraft.implementations.bukkit.gui

import net.benwoodworth.fastcraft.dependencies.gui.Gui
import net.benwoodworth.fastcraft.dependencies.gui.GuiFactory
import net.benwoodworth.fastcraft.dependencies.text.FcText
import net.benwoodworth.fastcraft.implementations.bukkit.BukkitFastCraft
import org.bukkit.Server
import javax.inject.Inject

class BukkitGuiFactory @Inject constructor(
        private var plugin: BukkitFastCraft,
        private var server: Server
) : GuiFactory {

    override fun chest(height: Int, title: FcText?): Gui.Chest {
        return BukkitGui.Chest(
                plugin,
                server,
                server.createInventory(null, 9 * height) //TODO
        )
    }

    override fun dispenser(title: FcText?): Gui.Dispenser {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hopper(title: FcText?): Gui.Hopper {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}