package net.benwoodworth.fastcraft.core.config

import net.benwoodworth.fastcraft.dependencies.config.ConfigManager
import net.benwoodworth.fastcraft.dependencies.server.Plugin
import java.nio.file.Path
import javax.inject.Inject

/**
 * Updates FastCraft's config files.
 */
class ConfigLoader @Inject constructor(
        pluginProvider: Plugin.Provider,
        private val configManager: ConfigManager
) {

    lateinit var pluginConfig: PluginConfig
        private set

    lateinit var dataConfig: DataConfig
        private set

    private val pluginConfigDir: Path
    private val pluginConfigFile: Path
    private val dataConfigFile: Path

    init {
        val fastCraftPlugin = pluginProvider.getPlugin("fastcraft")!!
        val fileExt = configManager.getFileExtension()

        pluginConfigDir = fastCraftPlugin.pluginDirectory
        pluginConfigFile = pluginConfigDir.resolve(fastCraftPlugin.configFileName)
        dataConfigFile = pluginConfigDir.resolve("data$fileExt")
    }

    fun load() {
        pluginConfig = PluginConfig(configManager.loadConfig(pluginConfigFile))
        dataConfig = DataConfig(configManager.loadConfig(pluginConfigFile))

        update()
        save()
    }

    fun save() {
        configManager.saveConfig(pluginConfig.config, pluginConfigFile)
        configManager.saveConfig(dataConfig.config, dataConfigFile)
    }

    private fun update() {
        dataConfig.config.header = listOf(
                "FastCraft, developed by Kepler_",
                "https://github.com/BenWoodworth/FastCraft",
                "",
                "language:",
                "    The FastCraft localization. Available languages:",
                "    EN (English), DE (Deutsch),    RU (Русский язык), CS (Čeština),",
                "    TR (Türkçe),  NL (Nederlands), VI (Tiếng Việt),   PL (Polskie),",
                "    ZH-CN (中文),  ZH-TW (台語)",
                "",
                "hidden-recipes:",
                "    A list of recipe ID's for recipes that should be hidden from the",
                "    FastCraft UI. Recipe ID's are listed under the list of ingredients."
        )

        for ((revision, updater) in updaters.toSortedMap()) {
            if (dataConfig.revision < revision) {
                updater()
                dataConfig.revision = revision
            }
        }
    }

    val updaters = mapOf(
            0 to {
                with(pluginConfig) {
                    language = "EN"
                    hiddenRecipes = emptyList()
                }

                with(dataConfig) {
                }
            }
    )
}
