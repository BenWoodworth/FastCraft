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
import kotlin.reflect.KMutableProperty0

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
            node["recipe-ids"].loadExprList(::recipeIds)
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

            fun load() {
                node["row"].loadInt(::row, 0 until this@Layout.height)
                node["column"].loadInt(::column, 0..8)
                node["width"].loadInt(::width, 1..9 - column)
                node["height"].loadInt(::height, 1..height - row)
            }
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

                override fun load() {
                    super.load()
                    node["row"].loadInt(::row, 0 until this@Layout.height)
                    node["column"].loadInt(::column, 0..8)
                }
            }

            fun load() {
                craftingGrid.load()
                craftAmount.load()
                refresh.load()
                page.load()
            }
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
                node["enable"].loadBoolean(::enable)
                node["item"].loadItem(::item, ::itemId)
            }
        }

        fun load() {
            node["height"].loadInt(::height, 1..6)
            recipes.load()
            buttons.load()
            background.load()
        }
    }

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

    private fun FcConfigNode.loadInt(
        property: KMutableProperty0<Int>,
        range: IntRange = Int.MIN_VALUE..Int.MAX_VALUE,
    ) {
        when (val newRow = getInt()) {
            null -> modify(property.get().coerceIn(range))
            !in range -> {
                property.set(range.first)
                logErr("$newRow is not in $range. Defaulting to ${property.get()}.")
            }
            else -> property.set(newRow)
        }
    }

    private fun FcConfigNode.loadBoolean(
        property: KMutableProperty0<Boolean>,
    ) {
        when (val newEnable = getBoolean()) {
            null -> property.set(modify(property.get()))
            else -> property.set(newEnable)
        }
    }

    private fun FcConfigNode.loadItem(
        itemProperty: KMutableProperty0<FcItemStack>,
        itemIdProperty: KMutableProperty0<String>,
    ) {
        when (val newItemId = getString()) {
            null -> modify(itemIdProperty.get())
            else -> when (val newItem = itemStackFactory.parseOrNull(newItemId)) {
                null -> logErr("Invalid item id: $newItemId. Defaulting to ${itemIdProperty.get()}.")
                else -> {
                    itemIdProperty.set(newItemId)
                    itemProperty.set(newItem)
                }
            }
        }
    }

    private fun FcConfigNode.loadExprList(
        property: KMutableProperty0<List<Expr>>,
    ) {
        when (val newDisableRecipes = getStringList()) {
            null -> modify(property.get().map { it.expression })
            else -> {
                val nonNulls = newDisableRecipes.filterNotNull()
                if (nonNulls.size != newDisableRecipes.size) {
                    modify(nonNulls) { "Removed null entries" }
                }
                property.set(nonNulls.map { Expr(it) })
            }
        }
    }
}
