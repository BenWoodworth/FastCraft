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
        val height by entry("height", 6) { getInt() }

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
            val item by entry("item", materials.craftingTable.id) { getString()?.let(materials::parseOrNull)?.id }
            val row by entry("row", 0) { getInt() }
            val column by entry("column", 8) { getInt() }
        }

        val craftAmountButton = section("craft-amount-button", CraftAmountButton())

        inner class CraftAmountButton : Configuration() {
            val enabled by entry("enabled", true) { getBoolean() }
            val item by entry("item", materials.anvil.id) { getString()?.let(materials::parseOrNull)?.id }
            val row by entry("row", 1) { getInt() }
            val column by entry("column", 8) { getInt() }
        }

        val refreshButton = section("refresh-button", RefreshButton())

        inner class RefreshButton : Configuration() {
            val enabled by entry("enabled", true) { getBoolean() }
            val item by entry("item", materials.netherStar.id) { getString()?.let(materials::parseOrNull)?.id }
            val row by entry("row", 2) { getInt() }
            val column by entry("column", 8) { getInt() }
        }

        val pageButton = section("page-button", PageButton())

        inner class PageButton : Configuration() {
            val enabled by entry("enabled", true) { getBoolean() }
            val item by entry("item", materials.ironSword.id) { getString()?.let(materials::parseOrNull)?.id }
            val row by entry("row", 5) { getInt() }
            val column by entry("column", 8) { getInt() }
        }

        val background = section("background", Background())

        inner class Background : Configuration() {
            val item by entry("item", materials.air.id) { getString()?.let(materials::parseOrNull)?.id }
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
