package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.bukkit.util.CauseTracker
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.server.FcTask
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.PluginManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class BukkitFcPlayerEvents_1_13 @Inject constructor(
    plugin: Plugin,
    fcPlayerProvider: FcPlayer.Provider,
    pluginManager: PluginManager,
    causeTracker: CauseTracker,
    fcTaskFactory: FcTask.Factory,
) : BukkitFcPlayerEvents_1_7(
    plugin = plugin,
    fcPlayerProvider = fcPlayerProvider,
    pluginManager = pluginManager,
    causeTracker = causeTracker,
    fcTaskFactory = fcTaskFactory,
) {
    override fun Block.isCraftingTable(): Boolean {
        return type == Material.CRAFTING_TABLE
    }
}
