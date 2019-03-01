package net.benwoodworth.fastcraft.bukkit.gui

import net.benwoodworth.fastcraft.bukkit.bukkit
import net.benwoodworth.fastcraft.bukkit.item.BukkitFcItemConverter
import net.benwoodworth.fastcraft.platform.gui.FcGui
import net.benwoodworth.fastcraft.platform.gui.FcGuiLayoutGrid
import net.benwoodworth.fastcraft.platform.server.FcPlayer
import net.benwoodworth.fastcraft.platform.text.FcLegacyText
import net.benwoodworth.fastcraft.platform.text.FcLegacyTextFactory
import org.bukkit.Server
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.PluginManager
import javax.inject.Inject

class BukkitFcGuiFactory_1_13_00_R01 @Inject constructor(
    private val plugin: Plugin,
    private val legacyTextFactory: FcLegacyTextFactory,
    private val server: Server,
    private val pluginManager: PluginManager,
    private val itemConverter: BukkitFcItemConverter
) : BukkitFcGuiFactory {

    override fun openChestGui(player: FcPlayer, title: FcLegacyText?, height: Int): FcGui<FcGuiLayoutGrid> {
        return BukkitFcGui_1_13_00_R01(
            player,
            { owner -> server.createInventory(owner, 9 * height, title?.bukkit?.legacyText) },
            { inventory -> BukkitFcGuiLayoutGrid_1_13_00_R01(9, height, inventory, itemConverter) },
            plugin,
            legacyTextFactory,
            pluginManager
        )
    }
}
