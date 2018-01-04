package net.benwoodworth.fastcraft.implementations.bukkit.gui

import net.benwoodworth.fastcraft.dependencies.gui.GuiBuilder
import net.benwoodworth.fastcraft.dependencies.text.Text
import net.benwoodworth.fastcraft.implementations.bukkit.BukkitFastCraft
import net.benwoodworth.fastcraft.implementations.bukkit.text.BukkitText
import javax.inject.Inject

class BukkitGuiBuilder @Inject constructor(
        private var plugin: BukkitFastCraft
) : GuiBuilder {

    private var height = 0
    private var title: String? = null

    override fun build() = BukkitGui(plugin, height, title)

    override fun height(height: Int) = also {
        this.height = height
    }

    override fun title(title: Text) = also {
        this.title = (title as BukkitText).text
    }
}