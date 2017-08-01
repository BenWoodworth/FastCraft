package net.benwoodworth.fastcraft.impl.bukkit.gui

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.dependencies.gui.Gui
import net.benwoodworth.fastcraft.dependencies.gui.ModuleGui

/**
 * Bukkit implementation of [ModuleGui].
 */
@Module
class BukkitModuleGui : ModuleGui {

    @Provides
    override fun guiBuilder(): Gui.Builder {
        TODO("not implemented")
    }
}
