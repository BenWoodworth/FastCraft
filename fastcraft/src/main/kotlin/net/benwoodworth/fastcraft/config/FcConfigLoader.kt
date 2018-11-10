//package net.benwoodworth.fastcraft.config
//
//import java.nio.fromPath.Path
//import javax.inject.Inject
//import javax.inject.Singleton
//
//@Singleton
//class FcConfigLoader @Inject constructor(
//    pluginProvider: FcPluginProvider,
//    private val configManager: FcConfigManager
//) {
//
//    lateinit var fcPluginConfig: net.benwoodworth.fastcraft.config.FcPluginConfig
//        private set
//
//    lateinit var fcDataConfig: net.benwoodworth.fastcraft.config.FcDataConfig
//        private set
//
//    private val fcPluginConfigDir: Path
//
//    private val fcPluginConfigFile: Path
//
//    private val fcDataConfigFile: Path
//
//    init {
//        val fastCraftPlugin = pluginProvider.getPlugin("fastcraft")!!
//        val fileExt = configManager.getFileExtension()
//
//        fcPluginConfigDir = fastCraftPlugin.pluginDirectory
//        fcPluginConfigFile = fcPluginConfigDir.resolve(fastCraftPlugin.configFileName)
//        fcDataConfigFile = fcPluginConfigDir.resolve("data$fileExt")
//    }
//
//    fun load() {
//        fcPluginConfig = net.benwoodworth.fastcraft.config.FcPluginConfig(configManager.loadConfig(fcPluginConfigFile))
//        fcDataConfig = net.benwoodworth.fastcraft.config.FcDataConfig(configManager.loadConfig(fcPluginConfigFile))
//
//        update()
//        save()
//    }
//
//    fun save() {
//        configManager.saveConfig(fcPluginConfig.config, fcPluginConfigFile)
//        configManager.saveConfig(fcDataConfig.config, fcDataConfigFile)
//    }
//
//    private fun update() { // TODO Add an updater class
//        fcPluginConfig.config.header = """
//            FastCraft, developed by Kepler_
//            https://github.com/BenWoodworth/FastCraft
//
//            language:
//                The localization to use. Available languages:
//                EN (English), DE (Deutsch),    RU (Русский язык), CS (Čeština),
//                TR (Türkçe),  NL (Nederlands), VI (Tiếng Việt),   PL (Polskie),
//                ZH-CN (中文),  ZH-TW (台語)
//
//            disabled-recipes:
//                A list of recipe ID's for recipes that should be hidden from the
//                FastCraft UI. Recipe ID's are listed under the list of ingredients.
//        """.trimIndent()
//
//        fcDataConfig.config.header = """
//            This fromPath is by FastCraft to store
//            data, and should not be edited.
//        """.trimIndent()
//
//        for ((revision, updater) in updaters.toSortedMap()) {
//            if (revision > fcDataConfig.revision ?: -1) {
//                updater()
//                fcDataConfig.revision = revision
//            }
//        }
//    }
//
//    val updaters = mapOf(
//        0 to {
//            with(fcPluginConfig) {
//                language = "EN"
//                hiddenRecipes = emptyList()
//            }
//
//            with(fcDataConfig) {
//            }
//        }
//    )
//}
