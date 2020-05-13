package net.benwoodworth.fastcraft

import net.benwoodworth.fastcraft.platform.config.FcConfig
import net.benwoodworth.fastcraft.platform.config.FcConfigNode
import net.benwoodworth.fastcraft.platform.server.FcLogger
import net.benwoodworth.fastcraft.platform.server.FcPluginData
import net.benwoodworth.fastcraft.platform.world.FcItem
import net.benwoodworth.fastcraft.platform.world.FcItemStack
import net.benwoodworth.fastcraft.util.Expr
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
    private var config: FcConfig = configFactory.create()
    private var modified: Boolean = false
    private var newFile: Boolean = false

    private val header: String = """
        https://github.com/BenWoodworth/FastCraft/wiki/Configuration
    """.trimIndent()

    val disableRecipes = DisableRecipes()

    inner class DisableRecipes {
        private val node: FcConfigNode
            get() = config["disable-recipes"]

        private val none = Regex("(?" + "!)") // Matches nothing

        private var recipeIds: List<Expr> = emptyList()
            set(values) {
                field = values
                recipeIdsRegex = values
                    .takeUnless { it.isEmpty() }
                    ?.joinToString("|") { it.toRegex().toString() }
                    ?.let { Regex(it) }
                    ?: none
            }

        var recipeIdsRegex: Regex = none
            private set

        fun load() {
            node["recipe-ids"].run {
                recipeIds = when (val newDisableRecipes = getStringList()) {
                    null -> recipeIds.also {
                        modify(recipeIds.map { it.expression })
                    }
                    else -> {
                        val nonNulls = newDisableRecipes.filterNotNull()
                        if (nonNulls.size != newDisableRecipes.size) {
                            modify(nonNulls) { "Removed null entries" }
                        }
                        nonNulls.map { Expr(it) }
                    }
                }
            }
        }
    }

    val layout = Layout()

    inner class Layout {
        private val node: FcConfigNode
            get() = config["layout"]

        var height: Int = 6
            private set

        val recipes = Recipes()

        inner class Recipes {
            private val node: FcConfigNode
                get() = this@Layout.node["recipes"]

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
                    val rowRange = 0 until this@Layout.height
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
                get() = this@Layout.node["buttons"]

            val craftingGrid = Button(
                key = "crafting-grid",
                enable = true,
                item = itemStackFactory.create(items.craftingTable),
                row = 0,
                column = 8,
            )

            val craftAmount = Button(
                key = "craft-amount",
                enable = true,
                item = itemStackFactory.create(items.anvil),
                row = 1,
                column = 8,
            )

            val refresh = Button(
                key = "refresh",
                enable = true,
                item = itemStackFactory.create(items.netherStar),
                row = 2,
                column = 8,
            )

            val page = Button(
                key = "page",
                enable = true,
                item = itemStackFactory.create(items.ironSword),
                row = 5,
                column = 8,
            )

            inner class Button(
                private val key: String,
                enable: Boolean,
                item: FcItemStack,
                row: Int,
                column: Int,
            ) : EnableItem(
                enable = enable,
                item = item,
            ) {
                override val node: FcConfigNode
                    get() = this@Buttons.node[key]

                var row: Int = row
                    private set

                var column: Int = column
                    private set

                //region fun load()
                override fun load() {
                    super.load()

                    node["row"].run {
                        val rowRange = 0 until this@Layout.height
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

        val background = Background()

        inner class Background : EnableItem(
            enable = false,
            item = itemStackFactory.create(items.lightGrayStainedGlassPane),
        ) {
            override val node: FcConfigNode
                get() = this@Layout.node["background"]
        }

        abstract inner class EnableItem(
            enable: Boolean,
            item: FcItemStack,
        ) {
            protected abstract val node: FcConfigNode

            var enable: Boolean = enable
                private set

            var item: FcItemStack = item
                private set

            private var itemId: String = item.type.id

            open fun load() {
                node["enable"].run {
                    enable = when (val newEnable = getBoolean()) {
                        null -> modify(enable)
                        else -> newEnable
                    }
                }

                node["item"].run {
                    item = when (val newItemId = getString()) {
                        null -> {
                            modify(itemId)
                            item
                        }
                        else -> when (val newItem = itemStackFactory.parseOrNull(newItemId)) {
                            null -> {
                                item.also {
                                    logErr("Invalid item id: $newItemId. Defaulting to ${itemId}.")
                                }
                            }
                            else -> {
                                itemId = newItemId
                                newItem
                            }
                        }
                    }
                }
            }
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

            recipes.load()
            buttons.load()
            background.load()
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

        disableRecipes.load()
        layout.load()

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
