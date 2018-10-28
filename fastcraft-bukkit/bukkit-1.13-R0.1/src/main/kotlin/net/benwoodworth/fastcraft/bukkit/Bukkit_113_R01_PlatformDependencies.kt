package net.benwoodworth.fastcraft.bukkit

import net.benwoodworth.fastcraft.bukkit.config.Bukkit_113_R01_FcConfigBuilder
import net.benwoodworth.fastcraft.bukkit.gui.Bukkit_113_R01_FcGuiBuilder
import net.benwoodworth.fastcraft.bukkit.item.Bukkit_113_R01_FcItemBuilder
import net.benwoodworth.fastcraft.bukkit.item.Bukkit_113_R01_FcItemTypes
import net.benwoodworth.fastcraft.bukkit.player.Bukkit_113_R01_FcPlayerProvider
import net.benwoodworth.fastcraft.bukkit.recipe.Bukkit_113_R01_FcRecipeService
import net.benwoodworth.fastcraft.bukkit.server.Bukkit_113_R01_FcLogger
import net.benwoodworth.fastcraft.bukkit.server.Bukkit_113_R01_FcPluginData
import net.benwoodworth.fastcraft.bukkit.server.Bukkit_113_R01_FcTaskBuilder
import net.benwoodworth.fastcraft.bukkit.text.Bukkit_113_R01_FcTextBuilder
import net.benwoodworth.fastcraft.platform.PlatformDependencies
import net.benwoodworth.fastcraft.platform.server.FcPluginData
import org.bukkit.plugin.Plugin

@Suppress("ClassName")
class Bukkit_113_R01_PlatformDependencies(
    private val plugin: Plugin
) : PlatformDependencies {

    override fun getLogger(): Bukkit_113_R01_FcLogger {
        return Bukkit_113_R01_FcLogger(plugin.logger)
    }

    override fun getPluginData(): FcPluginData {
        return Bukkit_113_R01_FcPluginData(plugin)
    }

    override fun createConfigBuilder(): Bukkit_113_R01_FcConfigBuilder {
        return Bukkit_113_R01_FcConfigBuilder
    }

    override fun createGuiBuilder(): Bukkit_113_R01_FcGuiBuilder {
        return Bukkit_113_R01_FcGuiBuilder()
    }

    override fun createItemBuilder(): Bukkit_113_R01_FcItemBuilder {
        return Bukkit_113_R01_FcItemBuilder
    }

    override fun getItemTypes(): Bukkit_113_R01_FcItemTypes {
        return Bukkit_113_R01_FcItemTypes
    }

    override fun getPlayerProvider(): Bukkit_113_R01_FcPlayerProvider {
        return Bukkit_113_R01_FcPlayerProvider
    }

    override fun getRecipeService(): Bukkit_113_R01_FcRecipeService {
        return Bukkit_113_R01_FcRecipeService
    }

    override fun createTaskBuilder(): Bukkit_113_R01_FcTaskBuilder {
        return Bukkit_113_R01_FcTaskBuilder(plugin)
    }

    override fun createTextBuilder(): Bukkit_113_R01_FcTextBuilder {
        return Bukkit_113_R01_FcTextBuilder
    }
}
