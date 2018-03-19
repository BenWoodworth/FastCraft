package net.benwoodworth.fastcraft.implementations.bukkit.gui

import net.benwoodworth.fastcraft.dependencies.gui.Gui
import net.benwoodworth.fastcraft.dependencies.gui.GuiFactory
import net.benwoodworth.fastcraft.dependencies.text.FcText
import javax.inject.Inject

class BukkitGuiFactory @Inject constructor(
        private val chestFactory: BukkitGui_ChestFactory,
        private val dispenserFactory: BukkitGui_DispenserFactory,
        private val hopperFactory: BukkitGui_HopperFactory
) : GuiFactory {

    override fun chest(height: Int, title: FcText?): Gui.Chest {
        server.cre
        return chestFactory.create()
    }

    override fun dispenser(title: FcText?): Gui.Dispenser {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hopper(title: FcText?): Gui.Hopper {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}