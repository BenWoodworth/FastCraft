package net.benwoodworth.fastcraft.bukkit.gui

import net.benwoodworth.fastcraft.bukkit.BukkitFastCraftPlugin
import net.benwoodworth.fastcraft.bukkit.text.BukkitFcTextConverter
import net.benwoodworth.fastcraft.platform.gui.FcGui
import net.benwoodworth.fastcraft.platform.gui.FcGuiLayoutGrid
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import org.bukkit.Server
import org.bukkit.plugin.PluginManager
import javax.inject.Inject

class BukkitFcGuiFactory_1_13_00_R01 @Inject constructor(
    private val plugin: BukkitFastCraftPlugin,
    private val legacyTextFactory: FcTextFactory,
    private val server: Server,
    private val pluginManager: PluginManager,
    private val textConverter: BukkitFcTextConverter,
    private val guiLayoutGridFactory: BukkitFcGuiLayoutGrid_1_13_00_R01Factory
) : BukkitFcGuiFactory {
    override fun openChestGui(player: FcPlayer, title: FcText?, height: Int): FcGui<FcGuiLayoutGrid> {
        val legacyTitle = title?.let {
            textConverter.toLegacy(it, player.locale)
        }

        return BukkitFcGui_1_13_00_R01(
            player,
            { owner -> server.createInventory(owner, 9 * height, legacyTitle) },
            { inventory -> guiLayoutGridFactory.create(9, height, inventory, player.locale) },
            plugin,
            legacyTextFactory,
            pluginManager
        )
    }
}
