package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.platform.player.FcPlayer
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.PluginManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class BukkitFcPlayerEvents_1_13 @Inject constructor(
    plugin: Plugin,
    playerProvider: FcPlayer.Provider,
    pluginManager: PluginManager,
) : BukkitFcPlayerEvents_1_7(
    plugin = plugin,
    playerProvider = playerProvider,
    pluginManager = pluginManager
) {
    override fun Block.isCraftingTable(): Boolean {
        return type == Material.CRAFTING_TABLE
    }
}
