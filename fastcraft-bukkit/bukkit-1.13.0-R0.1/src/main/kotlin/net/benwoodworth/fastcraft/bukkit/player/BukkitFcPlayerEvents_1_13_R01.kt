package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.platform.player.FcPlayerProvider
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.PluginManager
import javax.inject.Inject

open class BukkitFcPlayerEvents_1_13_R01 @Inject constructor(
    plugin: Plugin,
    playerProvider: FcPlayerProvider,
    pluginManager: PluginManager,
) : BukkitFcPlayerEvents_1_7_5_R01(
    plugin = plugin,
    playerProvider = playerProvider,
    pluginManager = pluginManager
) {
    override fun Block.isCraftingTable(): Boolean {
        return type == Material.CRAFTING_TABLE
    }
}
