package net.benwoodworth.fastcraft.config

import net.benwoodworth.fastcraft.platform.api.server.FcPluginRegistry
import java.nio.file.Path
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Updates FastCraft's config files.
 */
@Singleton
class FcConfigLoader @Inject constructor(
        pluginProvider: FcPluginRegistry,
        private val configManager: FcConfigManager
) {

    /**
     * The FastCraft plugin config.
     */
    lateinit var fcPluginConfig: net.benwoodworth.fastcraft.config.FcPluginConfig
        private set

    /**
     * The FastCraft data config.
     */
    lateinit var fcDataConfig: net.benwoodworth.fastcraft.config.FcDataConfig
        private set

    /**
     * The directory containing FastCraft configs.
     */
    private val fcPluginConfigDir: Path

    /**
     * The FastCraft config file.
     */
    private val fcPluginConfigFile: Path

    /**
     * The FastCraft data config file.
     */
    private val fcDataConfigFile: Path

    init {
        val fastCraftPlugin = pluginProvider.getPlugin("fastcraft")!!
        val fileExt = configManager.getFileExtension()

        fcPluginConfigDir = fastCraftPlugin.pluginDirectory
        fcPluginConfigFile = fcPluginConfigDir.resolve(fastCraftPlugin.configFileName)
        fcDataConfigFile = fcPluginConfigDir.resolve("data$fileExt")
    }

    /**
     * Load the configs.
     */
    fun load() {
        fcPluginConfig = net.benwoodworth.fastcraft.config.FcPluginConfig(configManager.loadConfig(fcPluginConfigFile))
        fcDataConfig = net.benwoodworth.fastcraft.config.FcDataConfig(configManager.loadConfig(fcPluginConfigFile))

        update()
        save()
    }

    /**
     * Save the configs.
     */
    fun save() {
        configManager.saveConfig(fcPluginConfig.config, fcPluginConfigFile)
        configManager.saveConfig(fcDataConfig.config, fcDataConfigFile)
    }

    /**
     * Update the configs.
     */
    private fun update() { // TODO Add an updater class
        fcPluginConfig.config.header = listOf(
                "FastCraft, developed by Kepler_",
                "https://github.com/BenWoodworth/FastCraft",
                "",
                "language:",
                "    The localization to use. Available languages:",
                "    EN (English), DE (Deutsch),    RU (Русский язык), CS (Čeština),",
                "    TR (Türkçe),  NL (Nederlands), VI (Tiếng Việt),   PL (Polskie),",
                "    ZH-CN (中文),  ZH-TW (台語)",
                "",
                "disabled-recipes:",
                "    A list of recipe ID's for recipes that should be hidden from the",
                "    FastCraft UI. Recipe ID's are listed under the list of ingredients."
        )

        fcDataConfig.config.header = listOf(
                "This file is by FastCraft to store",
                "data, and should not be edited."
        )

        for ((revision, updater) in updaters.toSortedMap()) {
            if (revision > fcDataConfig.revision ?: -1) {
                updater()
                fcDataConfig.revision = revision
            }
        }
    }

    /**
     * The updaters that update to a specific revision.
     */
    val updaters = mapOf(
            0 to {
                with(fcPluginConfig) {
                    language = "EN"
                    hiddenRecipes = emptyList()
                }

                with(fcDataConfig) {
                }
            }
    )
}
