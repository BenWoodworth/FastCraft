package net.benwoodworth.fastcraft.bukkit

import net.benwoodworth.fastcraft.bukkit.config.BukkitFcConfigFactory
import net.benwoodworth.fastcraft.bukkit.gui.BukkitFcGuiBuilder
import net.benwoodworth.fastcraft.bukkit.item.BukkitFcItemBuilder
import net.benwoodworth.fastcraft.bukkit.item.BukkitFcItemTypes
import net.benwoodworth.fastcraft.bukkit.player.BukkitFcPlayerProvider
import net.benwoodworth.fastcraft.bukkit.recipe.BukkitFcRecipeService
import net.benwoodworth.fastcraft.bukkit.server.BukkitFcLogger
import net.benwoodworth.fastcraft.bukkit.server.BukkitFcPluginData
import net.benwoodworth.fastcraft.bukkit.server.BukkitFcTaskBuilder
import net.benwoodworth.fastcraft.bukkit.text.BukkitFcTextBuilder
import net.benwoodworth.fastcraft.platform.FcPlatformDependencies
import net.benwoodworth.fastcraft.platform.server.FcPluginData
import org.bukkit.plugin.Plugin

class BukkitFcPlatformDependencies(
    private val plugin: Plugin
) : FcPlatformDependencies {

    override fun getLogger(): BukkitFcLogger {
        return BukkitFcLogger(plugin.logger)
    }

    override fun getPluginData(): FcPluginData {
        return BukkitFcPluginData(plugin)
    }

    override fun createConfigBuilder(): BukkitFcConfigFactory {
//        return BukkitFcConfigFactory
        TODO()
    }


    override fun createGuiBuilder(): BukkitFcGuiBuilder {
        return BukkitFcGuiBuilder()
    }

    override fun createItemBuilder(): BukkitFcItemBuilder {
//        return BukkitFcItemBuilder({createTextBuilder()})
        TODO()
    }

    override fun getItemTypes(): BukkitFcItemTypes {
//        return BukkitFcItemTypes
        TODO()
    }

    override fun getPlayerProvider(): BukkitFcPlayerProvider {
//        return BukkitFcPlayerProvider
        TODO()
    }

    override fun getRecipeService(): BukkitFcRecipeService {
        return BukkitFcRecipeService
    }

    override fun createTaskBuilder(): BukkitFcTaskBuilder {
        return BukkitFcTaskBuilder(plugin)
    }

    override fun createTextBuilder(): BukkitFcTextBuilder {
//        return BukkitFcTextBuilder()
        TODO()
    }
}
