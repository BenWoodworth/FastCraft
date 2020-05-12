package net.benwoodworth.fastcraft

import net.benwoodworth.fastcraft.data.Configuration
import net.benwoodworth.fastcraft.platform.config.FcConfig
import net.benwoodworth.fastcraft.platform.item.FcMaterial
import net.benwoodworth.fastcraft.platform.server.FcPluginData
import java.nio.file.Files
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FastCraftConfig @Inject constructor(
    private val configFactory: FcConfig.Factory,
    private val pluginData: FcPluginData,
    private val materials: FcMaterial.Factory,
) : Configuration() {
    val disabledRecipes by entry("disabled-recipes", emptyList()) { getStringList()?.filterNotNull() }

    val fastCraftUi = section("fastcraft-ui", FastCraftUi())

    inner class FastCraftUi : Configuration() {
        val recipeButtons = section("recipe-buttons", RecipesButtons())

        inner class RecipesButtons : Configuration() {
            val enabled by entry("enabled", true) { getBoolean() }
            val row by entry("row", 0) { getInt() }
            val column by entry("column", 0) { getInt() }
            val width by entry("width", 7) { getInt() }
            val height by entry("height", 6) { getInt() }
        }

        val craftingGridButton = section("crafting-grid-button", CraftingGridButton())

        inner class CraftingGridButton : Configuration() {
            val enabled by entry("enabled", true) { getBoolean() }
            val item by entry("item", "minecraft:crafting_table") { getString() }
            val row by entry("row", 0) { getInt() }
            val column by entry("column", 0) { getInt() }
        }

        val craftAmountButton = section("craft-amount-button", CraftAmountButton())

        inner class CraftAmountButton : Configuration() {
            val enabled by entry("enabled", true) { getBoolean() }
            val item by entry("item", "minecraft:anvil") { getString() }
            val row by entry("row", 0) { getInt() }
            val column by entry("column", 0) { getInt() }
        }

        val refreshButton = section("refresh-button", RefreshButton())

        inner class RefreshButton : Configuration() {
            val enabled by entry("enabled", true) { getBoolean() }
            val item by entry("item", "minecraft:nether_star") { getString() }
            val row by entry("row", 0) { getInt() }
            val column by entry("column", 0) { getInt() }
        }

        val pageButton = section("page-button", PageButton())

        inner class PageButton : Configuration() {
            val enabled by entry("enabled", true) { getBoolean() }
            val item by entry("item", "minecraft:iron_sword") { getString() }
            val row by entry("row", 0) { getInt() }
            val column by entry("column", 0) { getInt() }
        }

        val background = section("background", Background())

        inner class Background : Configuration() {
            val enabled by entry("enabled", true) { getBoolean() }
            val item by entry("item", "minecraft:air") { getString() }
        }
    }

    fun load() {
        val file = pluginData.configFile
        Files.createDirectories(file.parent)

        val config = if (Files.exists(file)) {
            configFactory.load(file)
        } else {
            configFactory.create()
        }

        load(config)
        config.save(file)
    }

    init {
        load()
    }
}
