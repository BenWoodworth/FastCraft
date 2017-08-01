package net.benwoodworth.fastcraft.impl.bukkit.gui

import dagger.Module
import dagger.Provides
import net.benwoodworth.fastcraft.dependencies.gui.Gui
import net.benwoodworth.fastcraft.dependencies.gui.ModuleGui
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin as Bukkit_Plugin

/**
 * Bukkit implementation of [ModuleGui].
 */
@Module
class BukkitModuleGui(
        plugin: Bukkit_Plugin
) : ModuleGui {

    init {
        Bukkit.getPluginManager().registerEvents(BukkitGui.Listeners(), plugin)
    }

    @Provides
    override fun guiBuilder(): Gui.Builder {
        return BukkitGui.Builder()
    }
}
