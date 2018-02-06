package net.benwoodworth.fastcraft.implementations.bukkit.api.gui

import net.benwoodworth.fastcraft.api.gui.Gui
import net.benwoodworth.fastcraft.api.gui.GuiFactory
import net.benwoodworth.fastcraft.dependencies.text.Text
import net.benwoodworth.fastcraft.implementations.bukkit.BukkitFastCraft
import net.benwoodworth.fastcraft.implementations.bukkit.text.BukkitText
import javax.inject.Inject

class BukkitGuiFactory @Inject constructor(
        private var plugin: BukkitFastCraft
) : GuiFactory {

    override fun chest(height: Int, title: Text?): Gui.Chest {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dispenser(title: Text?): Gui.Dispenser {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hopper(title: Text?): Gui.Hopper {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}