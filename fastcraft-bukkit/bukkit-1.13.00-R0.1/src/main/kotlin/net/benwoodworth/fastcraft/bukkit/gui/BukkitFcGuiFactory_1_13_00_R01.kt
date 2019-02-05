package net.benwoodworth.fastcraft.bukkit.gui

import net.benwoodworth.fastcraft.bukkit.bukkit
import net.benwoodworth.fastcraft.platform.gui.FcGui
import net.benwoodworth.fastcraft.platform.gui.FcGuiLayoutGrid
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.text.FcLegacyText
import net.benwoodworth.fastcraft.platform.text.FcLegacyTextFactory
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import javax.inject.Inject

class BukkitFcGuiFactory_1_13_00_R01 @Inject constructor(
    private val plugin: Plugin,
    private val legacyTextFactory: FcLegacyTextFactory
) : BukkitFcGuiFactory {

    override fun openChestGui(player: FcPlayer, title: FcLegacyText?, height: Int): FcGui<FcGuiLayoutGrid> {
        return BukkitFcGui_1_13_00_R01(
            player,
            { owner -> Bukkit.createInventory(owner, height, title?.bukkit?.legacyText) },
            { inventory -> BukkitFcGuiLayoutGrid_1_13_00_R01(9, height, inventory) },
            plugin,
            legacyTextFactory
        )
    }
}
