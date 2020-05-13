package net.benwoodworth.fastcraft

import net.benwoodworth.fastcraft.platform.config.FcConfig
import net.benwoodworth.fastcraft.platform.config.FcConfigNode
import net.benwoodworth.fastcraft.platform.server.FcLogger
import net.benwoodworth.fastcraft.platform.server.FcPluginData
import net.benwoodworth.fastcraft.platform.world.FcItem
import net.benwoodworth.fastcraft.platform.world.FcItemStack
import java.nio.file.Files
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FastCraftConfig @Inject constructor(
    private val configFactory: FcConfig.Factory,
    private val pluginData: FcPluginData,
    private val items: FcItem.Factory,
    private val itemStackFactory: FcItemStack.Factory,
    private val logger: FcLogger,
) {
    private val matchNothing = Regex("(?" + "!)")

    private var config: FcConfig = configFactory.create()
    private var modified: Boolean = false
    private var newFile: Boolean = false

    private val header: String = """
        https://github.com/BenWoodworth/FastCraft/wiki/Configuration
    """.trimIndent()

    private fun wildcardToRegex(expression: String): String {
        return Regex.escape(expression)
            .replace("*", """\E.*\Q""")
    }

    private var disabledRecipeIds: List<String> = emptyList()
        set(values) {
            field = values

            disabledRecipes = values
                .map { wildcardToRegex(it) }
                .takeIf { it.any() }
                ?.let { Regex(it.joinToString("|")) }
                ?: matchNothing
        }

    var disabledRecipes: Regex = matchNothing
        private set

    val fastCraftUi = FastCraftUi()

    inner class FastCraftUi {
        private val node: FcConfigNode
            get() = config["fastcraft-ui"]

        var height: Int = 6
            private set

        var backgroundItem: FcItemStack = itemStackFactory.create(items.air)
            private set

        private var backgroundItemId: String = backgroundItem.type.id

        val recipes = Recipes()

        inner class Recipes {
            private val node: FcConfigNode
                get() = this@FastCraftUi.node["recipes"]

            var row: Int = 0
                private set

            var column: Int = 0
                private set

            var width: Int = 7
                private set

            var height: Int = 6
                private set

            //region fun load()
            fun load() {
                node["row"].run {
                    val rowRange = 0 until this@FastCraftUi.height
                    row = when (val newRow = getInt()) {
                        null -> modify(row.coerceIn(rowRange))
                        !in rowRange -> {
                            rowRange.first.also {
                                logErr("$newRow is not in $rowRange. Defaulting to $it.")
                            }
                        }
                        else -> newRow
                    }
                }

                node["column"].run {
                    val columnRange = 0..8
                    column = when (val newColumn = getInt()) {
                        null -> modify(column.coerceIn(columnRange))
                        !in columnRange -> {
                            columnRange.first.also {
                                logErr("$newColumn is not in $columnRange. Defaulting to $it.")
                            }
                        }
                        else -> newColumn
                    }
                }

                node["width"].run {
                    val widthRange = 1..9 - column
                    width = when (val newWidth = getInt()) {
                        null -> modify(width.coerceIn(widthRange))
                        !in widthRange -> {
                            widthRange.first.also {
                                logErr("$newWidth is not in $widthRange. Defaulting to $it.")
                            }
                        }
                        else -> newWidth
                    }
                }

                node["height"].run {
                    val heightRange = 1..height - row
                    height = when (val newHeight = getInt()) {
                        null -> modify(height.coerceIn(heightRange))
                        !in heightRange -> {
                            heightRange.first.also {
                                logErr("$newHeight is not in $heightRange. Defaulting to $it.")
                            }
                        }
                        else -> newHeight
                    }
                }
            }
            //endregion
        }

        val buttons = Buttons()

        inner class Buttons {
            private val node: FcConfigNode
                get() = this@FastCraftUi.node["buttons"]

            val craftingGrid = CraftingGrid()

            inner class CraftingGrid {
                private val node: FcConfigNode
                    get() = this@Buttons.node["crafting-grid"]

                var enabled: Boolean = true
                    private set

                var item: FcItem = items.craftingTable
                    private set

                var row: Int = 0
                    private set

                var column: Int = 8
                    private set

                //region fun load()
                fun load() {
                    node["enabled"].run {
                        enabled = when (val newEnabled = getBoolean()) {
                            null -> modify(enabled)
                            else -> newEnabled
                        }
                    }

                    node["row"].run {
                        val rowRange = 0 until this@FastCraftUi.height
                        row = when (val newRow = getInt()) {
                            null -> modify(row.coerceIn(rowRange))
                            !in rowRange -> {
                                rowRange.first.also {
                                    logErr("$newRow is not in $rowRange. Defaulting to $it.")
                                }
                            }
                            else -> newRow
                        }
                    }

                    node["column"].run {
                        val columnRange = 0..8
                        column = when (val newColumn = getInt()) {
                            null -> modify(column.coerceIn(columnRange))
                            !in columnRange -> {
                                columnRange.first.also {
                                    logErr("$newColumn is not in $columnRange. Defaulting to $it.")
                                }
                            }
                            else -> newColumn
                        }
                    }

                    node["item"].run {
                        item = when (val newItemId = getString()) {
                            null -> {
                                modify(item.id)
                                item
                            }
                            else -> when (val newItem = items.parseOrNull(newItemId)) {
                                null -> {
                                    item.also {
                                        logErr("Invalid item id: $newItemId. Defaulting to ${it.id}.")
                                    }
                                }
                                else -> newItem
                            }
                        }
                    }
                }
                //endregion
            }

            val craftAmount = CraftAmount()

            inner class CraftAmount {
                private val node: FcConfigNode
                    get() = this@Buttons.node["craft-amount"]

                var enabled: Boolean = true
                    private set

                var item: FcItem = items.anvil
                    private set

                var row: Int = 1
                    private set

                var column: Int = 8
                    private set

                //region fun load()
                fun load() {
                    node["enabled"].run {
                        enabled = when (val newEnabled = getBoolean()) {
                            null -> modify(enabled)
                            else -> newEnabled
                        }
                    }

                    node["row"].run {
                        val rowRange = 0 until this@FastCraftUi.height
                        row = when (val newRow = getInt()) {
                            null -> modify(row.coerceIn(rowRange))
                            !in rowRange -> {
                                rowRange.first.also {
                                    logErr("$newRow is not in $rowRange. Defaulting to $it.")
                                }
                            }
                            else -> newRow
                        }
                    }

                    node["column"].run {
                        val columnRange = 0..8
                        column = when (val newColumn = getInt()) {
                            null -> modify(column.coerceIn(columnRange))
                            !in columnRange -> {
                                columnRange.first.also {
                                    logErr("$newColumn is not in $columnRange. Defaulting to $it.")
                                }
                            }
                            else -> newColumn
                        }
                    }

                    node["item"].run {
                        item = when (val newItemId = getString()) {
                            null -> {
                                modify(item.id)
                                item
                            }
                            else -> when (val newItem = items.parseOrNull(newItemId)) {
                                null -> {
                                    item.also {
                                        logErr("Invalid item id: $newItemId. Defaulting to ${it.id}.")
                                    }
                                }
                                else -> newItem
                            }
                        }
                    }
                }
                //endregion
            }

            val refresh = Refresh()

            inner class Refresh {
                private val node: FcConfigNode
                    get() = this@Buttons.node["refresh"]

                var enabled: Boolean = true
                    private set

                var item: FcItem = items.netherStar
                    private set

                var row: Int = 2
                    private set

                var column: Int = 8
                    private set

                //region fun load()
                fun load() {
                    node["enabled"].run {
                        enabled = when (val newEnabled = getBoolean()) {
                            null -> modify(enabled)
                            else -> newEnabled
                        }
                    }

                    node["row"].run {
                        val rowRange = 0 until this@FastCraftUi.height
                        row = when (val newRow = getInt()) {
                            null -> modify(row.coerceIn(rowRange))
                            !in rowRange -> {
                                rowRange.first.also {
                                    logErr("$newRow is not in $rowRange. Defaulting to $it.")
                                }
                            }
                            else -> newRow
                        }
                    }

                    node["column"].run {
                        val columnRange = 0..8
                        column = when (val newColumn = getInt()) {
                            null -> modify(column.coerceIn(columnRange))
                            !in columnRange -> {
                                columnRange.first.also {
                                    logErr("$newColumn is not in $columnRange. Defaulting to $it.")
                                }
                            }
                            else -> newColumn
                        }
                    }

                    node["item"].run {
                        item = when (val newItemId = getString()) {
                            null -> {
                                modify(item.id)
                                item
                            }
                            else -> when (val newItem = items.parseOrNull(newItemId)) {
                                null -> {
                                    item.also {
                                        logErr("Invalid item id: $newItemId. Defaulting to ${it.id}.")
                                    }
                                }
                                else -> newItem
                            }
                        }
                    }
                }
                //endregion
            }

            val page = Page()

            inner class Page {
                private val node: FcConfigNode
                    get() = this@Buttons.node["page"]

                var enabled: Boolean = true
                    private set

                var item: FcItem = items.ironSword
                    private set

                var row: Int = 5
                    private set

                var column: Int = 8
                    private set

                //region fun load()
                fun load() {
                    node["enabled"].run {
                        enabled = when (val newEnabled = getBoolean()) {
                            null -> modify(enabled)
                            else -> newEnabled
                        }
                    }

                    node["row"].run {
                        val rowRange = 0 until this@FastCraftUi.height
                        row = when (val newRow = getInt()) {
                            null -> modify(row.coerceIn(rowRange))
                            !in rowRange -> {
                                rowRange.first.also {
                                    logErr("$newRow is not in $rowRange. Defaulting to $it.")
                                }
                            }
                            else -> newRow
                        }
                    }

                    node["column"].run {
                        val columnRange = 0..8
                        column = when (val newColumn = getInt()) {
                            null -> modify(column.coerceIn(columnRange))
                            !in columnRange -> {
                                columnRange.first.also {
                                    logErr("$newColumn is not in $columnRange. Defaulting to $it.")
                                }
                            }
                            else -> newColumn
                        }
                    }

                    node["item"].run {
                        item = when (val newItemId = getString()) {
                            null -> {
                                modify(item.id)
                                item
                            }
                            else -> when (val newItem = items.parseOrNull(newItemId)) {
                                null -> {
                                    item.also {
                                        logErr("Invalid item id: $newItemId. Defaulting to ${it.id}.")
                                    }
                                }
                                else -> newItem
                            }
                        }
                    }
                }
                //endregion
            }

            //region fun load()
            fun load() {
                craftingGrid.load()
                craftAmount.load()
                refresh.load()
                page.load()
            }
            //endregion
        }

        //region fun load()
        fun load() {
            height = node["height"].run {
                val heightRange = 1..6
                when (val newHeight = getInt()) {
                    null -> {
                        modify(height)
                    }
                    !in heightRange -> {
                        height.also {
                            logErr("Invalid height: $newHeight. Must be in $heightRange. Defaulting to $it")
                        }
                    }
                    else -> newHeight
                }
            }

            node["background-item"].run {
                backgroundItem = when (val newItemId = getString()) {
                    null -> {
                        modify(backgroundItemId)
                        backgroundItem
                    }
                    else -> when (val newItem = itemStackFactory.parseOrNull(newItemId)) {
                        null -> {
                            backgroundItem.also {
                                logErr("Invalid item id: $newItemId. Defaulting to ${backgroundItemId}.")
                            }
                        }
                        else -> {
                            backgroundItemId = newItemId
                            newItem
                        }
                    }
                }
            }

            recipes.load()
            buttons.load()
        }
        //endregion
    }

    //region fun load()
    fun load() {
        val file = pluginData.configFile
        Files.createDirectories(file.parent)

        config = if (Files.exists(file)) {
            modified = false
            newFile = false
            try {
                configFactory.load(file)
            } catch (e: Exception) {
                logger.error("Error loading ${file.fileName}: ${e.message}")
                logger.info("Using default configuration")
                return
            }
        } else {
            modified = true
            newFile = true
            configFactory.create()
        }

        if (config.headerComment != header) {
            config.headerComment = header
            modified = true
        }

        config["disabled-recipes"].run {
            disabledRecipeIds = when (val newDisabledRecipes = getStringList()) {
                null -> modify(disabledRecipeIds)
                else -> {
                    val nonNulls = newDisabledRecipes.filterNotNull()
                    if (nonNulls.size != newDisabledRecipes.size) {
                        modify(nonNulls) { "Removed null entries" }
                    } else {
                        nonNulls
                    }
                }
            }
        }

        fastCraftUi.load()

        if (modified) {
            config.save(file)
        }

        if (newFile) {
            logger.info("Created ${file.fileName}")
        }

        modified = false
        newFile = false
    }
    //endregion

    init {
        load()
    }

    private inline fun <T> FcConfigNode.modify(
        newValue: T,
        message: (newValue: T) -> String = { "Set value to $it" },
    ): T {
        if (!newFile) {
            logger.info("${pluginData.configFile.fileName} [$path]: ${message(newValue)}")
        }

        set(newValue)
        modified = true
        return newValue
    }

    private fun FcConfigNode.logErr(message: String) {
        logger.error("${pluginData.configFile.fileName} [$path]: $message")
    }
}
