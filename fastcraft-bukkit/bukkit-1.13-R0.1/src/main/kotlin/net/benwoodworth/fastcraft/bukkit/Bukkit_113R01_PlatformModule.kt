package net.benwoodworth.fastcraft.bukkit

import net.benwoodworth.fastcraft.bukkit.config.Bukkit_113R01_FcConfigBuilder
import net.benwoodworth.fastcraft.bukkit.gui.Bukkit_113R01_FcGuiBuilder
import net.benwoodworth.fastcraft.bukkit.item.Bukkit_113R01_FcItemBuilder
import net.benwoodworth.fastcraft.bukkit.item.Bukkit_113R01_FcItemTypes
import net.benwoodworth.fastcraft.bukkit.player.Bukkit_113R01_FcPlayerProvider
import net.benwoodworth.fastcraft.bukkit.recipe.Bukkit_113R01_FcRecipeService
import net.benwoodworth.fastcraft.bukkit.server.Bukkit_113R01_FcLogger
import net.benwoodworth.fastcraft.bukkit.server.Bukkit_113R01_FcTaskBuilder
import net.benwoodworth.fastcraft.bukkit.text.Bukkit_113R01_FcTextBuilder
import net.benwoodworth.fastcraft.platform.PlatformModule
import org.bukkit.plugin.Plugin
import java.nio.file.Path

@Suppress("ClassName")
class Bukkit_113R01_PlatformModule(
    private val plugin: Plugin
) : PlatformModule {

    override fun getLogger(): Bukkit_113R01_FcLogger {
        return Bukkit_113R01_FcLogger(plugin.logger)
    }

    override fun getDataFolder(): Path {
        return plugin.dataFolder.toPath()
    }

    override fun getConfigFile(): Path {
        return getDataFolder().resolve("config.yml")
    }

    override fun createConfigBuilder(): Bukkit_113R01_FcConfigBuilder {
        return Bukkit_113R01_FcConfigBuilder
    }

    override fun createGuiBuilder(): Bukkit_113R01_FcGuiBuilder {
        return Bukkit_113R01_FcGuiBuilder()
    }

    override fun createItemBuilder(): Bukkit_113R01_FcItemBuilder {
        return Bukkit_113R01_FcItemBuilder
    }

    override fun getItemTypes(): Bukkit_113R01_FcItemTypes {
        return Bukkit_113R01_FcItemTypes
    }

    override fun getPlayerProvider(): Bukkit_113R01_FcPlayerProvider {
        return Bukkit_113R01_FcPlayerProvider
    }

    override fun getRecipeService(): Bukkit_113R01_FcRecipeService {
        return Bukkit_113R01_FcRecipeService
    }

    override fun createTaskBuilder(): Bukkit_113R01_FcTaskBuilder {
        return Bukkit_113R01_FcTaskBuilder(plugin)
    }

    override fun createTextBuilder(): Bukkit_113R01_FcTextBuilder {
        return Bukkit_113R01_FcTextBuilder
    }
}
